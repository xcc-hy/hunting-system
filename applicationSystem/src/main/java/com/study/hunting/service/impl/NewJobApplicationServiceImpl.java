package com.study.hunting.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.study.hunting.client.JobClient;
import com.study.hunting.client.UserClient;
import com.study.hunting.domain.Job;
import com.study.hunting.domain.NewJobApplication;
import com.study.hunting.dao.NewJobApplicationMapper;
import com.study.hunting.enums.ResponseCode;
import com.study.hunting.service.NewJobApplicationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.study.hunting.util.BeanUtils;
import com.study.hunting.util.DateTimeUtils;
import com.study.hunting.vo.JobVO;
import com.study.hunting.vo.NewJobApplicationVO;
import com.study.hunting.vo.ResultVO;
import io.seata.core.context.RootContext;
import io.seata.core.exception.TransactionException;
import io.seata.spring.annotation.GlobalTransactional;
import io.seata.tm.TransactionManagerHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xcc
 * @since 2022-12-30
 */
@Service
public class NewJobApplicationServiceImpl extends ServiceImpl<NewJobApplicationMapper, NewJobApplication> implements NewJobApplicationService {

    @Autowired
    private JobClient jobClient;

    @Autowired
    private UserClient userClient;

    private void addAuditName(List<NewJobApplication> jobs) throws Exception {
        Map<Integer, String> userNameMap = new HashMap<>(jobs.size());
        for (NewJobApplication job : jobs) {
            if (job.getAuditId() != null) userNameMap.put(job.getAuditId(), "");
        }
        for (Integer id : userNameMap.keySet()) {
            userNameMap.put(id, userClient.getNameById(id).getData());
        }
        for (int i = 0; i < jobs.size(); i++) {
            NewJobApplication job = jobs.get(i);
            NewJobApplicationVO jobVO;
            if (job instanceof NewJobApplicationVO) {
                jobVO = (NewJobApplicationVO) job;
            } else {
                jobVO = BeanUtils.copyFromFather(job, new NewJobApplicationVO());
            }
            if (job.getAuditId() != null) jobVO.setAuditName(userNameMap.get(job.getAuditId()));
            jobs.set(i, jobVO);
        }
    }

    @Override
    @GlobalTransactional
    public ResultVO createApplication(Integer userId, Job job) throws TransactionException {
        ResultVO result = new ResultVO();
        if (userClient.isEnterprise(userId).getData()) {
            try {
                ResultVO<Integer> jobResult = jobClient.createJob(userId, job);
                if (jobResult.getCode() == ResponseCode.SUCCESS.getCode()) {
                    NewJobApplication newJobApplication = new NewJobApplication();
                    newJobApplication.setApplicationId(userId);
                    newJobApplication.setApplicationTime(DateTimeUtils.nowTimeFormat());
                    newJobApplication.setJobId(jobResult.getData());
                    newJobApplication.setStatus("0");
                    baseMapper.insert(newJobApplication);
                    result.setResponseCode(ResponseCode.SUCCESS);
                } else {
                    TransactionManagerHolder.get().rollback(RootContext.getXID());
                    result.setResponseCode(ResponseCode.FAIL);
                }
            } catch (Exception e) {
                TransactionManagerHolder.get().rollback(RootContext.getXID());
                result.setResponseCode(ResponseCode.FAIL);
            }
        } else {
            result.setResponseCode(ResponseCode.NO_OPERATION_PERMISSION);
        }
        return result;
    }

    @Override
    @GlobalTransactional
    public ResultVO passApplication(Integer id, Integer userId, String reviewReason) throws TransactionException {
        ResultVO result = new ResultVO();
        if (userClient.isManager(userId)) {
            NewJobApplication newJobApplication = baseMapper.selectById(id);
            if ("0".equals(newJobApplication.getStatus())) {
                ResultVO resultVO = jobClient.passJob(newJobApplication.getJobId(), userId);
                if (resultVO.getCode() == ResponseCode.SUCCESS.getCode()) {
                    newJobApplication.setAuditTime(DateTimeUtils.nowTimeFormat());
                    newJobApplication.setReviewReason(reviewReason);
                    newJobApplication.setStatus("1");
                    baseMapper.updateById(newJobApplication);
                    result.setResponseCode(ResponseCode.SUCCESS);
                } else {
                    TransactionManagerHolder.get().rollback(RootContext.getXID());
                    result.setResponseCode(ResponseCode.FAIL);
                }
            } else {
                result.setResponseCode(ResponseCode.FAIL);
            }
        } else {
            result.setResponseCode(ResponseCode.NO_OPERATION_PERMISSION);
        }
        return result;
    }

    @Override
    @GlobalTransactional
    public ResultVO refuseApplication(Integer id, Integer userId, String reviewReason) throws TransactionException {
        ResultVO result = new ResultVO();
        if (userClient.isManager(userId)) {
            NewJobApplication newJobApplication = baseMapper.selectById(id);
            if ("0".equals(newJobApplication.getStatus())) {
                ResultVO resultVO = jobClient.refuseJob(newJobApplication.getJobId(), userId);
                if (resultVO.getCode() == ResponseCode.SUCCESS.getCode()) {
                    newJobApplication.setAuditTime(DateTimeUtils.nowTimeFormat());
                    newJobApplication.setReviewReason(reviewReason);
                    newJobApplication.setStatus("2");
                    baseMapper.updateById(newJobApplication);
                    result.setResponseCode(ResponseCode.SUCCESS);
                } else {
                    TransactionManagerHolder.get().rollback(RootContext.getXID());
                    result.setResponseCode(ResponseCode.FAIL);
                }
            } else {
                result.setResponseCode(ResponseCode.FAIL);
            }
        } else {
            result.setResponseCode(ResponseCode.NO_OPERATION_PERMISSION);
        }
        return result;
    }

    @Override
    public ResultVO<List<NewJobApplication>> queryByJobId(Integer userId, Integer jobId) throws Exception {
        ResultVO<List<NewJobApplication>> result = new ResultVO<>();
        List<NewJobApplication> applicationList = baseMapper.selectList(new QueryWrapper<NewJobApplication>()
                .eq("job_id", jobId).orderByDesc("audit_time"));
        if (applicationList == null || applicationList.size() == 0) {
            result.setData(applicationList);
        } else {
            if (userClient.isManager(userId)) { // 管理员可查询
                addAuditName(applicationList);
                result.setData(applicationList);
            } else if (userId.equals(applicationList.get(0).getApplicationId())) { // 工作的申请者可查询
                result.setData(applicationList);
            } else {
                result.setResponseCode(ResponseCode.NO_OPERATION_PERMISSION);
            }
        }
        return result;
    }

    @Override
    public ResultVO reapplyJob(Integer userId, Integer jobId) {
        ResultVO result = new ResultVO();
        if (baseMapper.selectCount(new QueryWrapper<NewJobApplication>()
                .eq("job_id", jobId)
                .eq("status", "0")
                .eq("application_id", userId)) == 0) { // 当前没有申请
            Job job = jobClient.getJobDetailById(jobId).getData();
            if (job == null || !userId.equals(job.getOwnerId())) {
                result.setResponseCode(ResponseCode.NO_OPERATION_PERMISSION);
            } else if ("2".equals(job.getStatus()) || "4".equals(job.getStatus()) || "5".equals(job.getStatus())){
                ResultVO reapply = jobClient.reapply(jobId, userId);
                if (reapply.getCode() == ResponseCode.SUCCESS.getCode()) {
                    NewJobApplication newJobApplication = new NewJobApplication();
                    newJobApplication.setApplicationId(userId);
                    newJobApplication.setApplicationTime(DateTimeUtils.nowTimeFormat());
                    newJobApplication.setJobId(jobId);
                    newJobApplication.setStatus("0");
                    baseMapper.insert(newJobApplication);
                    result.setResponseCode(ResponseCode.SUCCESS);
                } else {
                    result.setResponseCode(ResponseCode.FAIL);
                }
            } else {
                result.setResponseCode(ResponseCode.INVALID_OPERATION);
            }
        } else {
            result.setResponseCode(ResponseCode.INVALID_OPERATION);
        }
        return result;
    }

    @Override
    @GlobalTransactional
    public ResultVO freezeApplication(Integer jobId, Integer userId, String reviewReason) throws TransactionException {
        ResultVO result =  jobClient.freezeJob2(jobId, userId);
        if (result.getCode() == ResponseCode.SUCCESS.getCode()) {
            NewJobApplication newJobApplication = new NewJobApplication();
            newJobApplication.setApplicationTime(DateTimeUtils.nowTimeFormat());
            newJobApplication.setApplicationId(userId);
            newJobApplication.setJobId(jobId);
            newJobApplication.setAuditId(userId);
            newJobApplication.setStatus("4");
            newJobApplication.setAuditTime(DateTimeUtils.nowTimeFormat());
            newJobApplication.setReviewReason(reviewReason);
            baseMapper.insert(newJobApplication);
        }
        return result;
    }

    @Override
    public ResultVO recallApplication(Integer jobId, Integer userId) {
        ResultVO result = new ResultVO();
        if (jobId == null) {
            result.setResponseCode(ResponseCode.PARAMETER_ILLEGAL);
        } else {
            List<Object> newJobApplications = baseMapper.selectObjs(new QueryWrapper<NewJobApplication>()
                    .select("id")
                    .eq("job_id", jobId)
                    .eq("status", "0")
                    .eq("application_id", userId));
            if (newJobApplications.size() == 1) {
                if (jobClient.recallJob(jobId, userId).getCode() == ResponseCode.SUCCESS.getCode()) {
                    baseMapper.deleteById((Integer) newJobApplications.get(0));
                    result.setResponseCode(ResponseCode.SUCCESS);
                } else {
                    result.setResponseCode(ResponseCode.FAIL);
                }
            } else {
                result.setResponseCode(ResponseCode.INVALID_OPERATION);
            }
        }
        return result;
    }
}
