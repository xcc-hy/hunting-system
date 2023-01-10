package com.study.hunting.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.study.hunting.client.UserClient;
import com.study.hunting.domain.Job;
import com.study.hunting.domain.JobUserRelation;
import com.study.hunting.dao.JobUserRelationMapper;
import com.study.hunting.enums.ResponseCode;
import com.study.hunting.service.JobService;
import com.study.hunting.service.JobUserRelationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.study.hunting.util.BeanUtils;
import com.study.hunting.util.DateTimeUtils;
import com.study.hunting.vo.JobUserRelationVO;
import com.study.hunting.vo.JobVO;
import com.study.hunting.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xcc
 * @since 2022-10-02
 */
@Service
public class JobUserRelationServiceImpl extends ServiceImpl<JobUserRelationMapper, JobUserRelation> implements JobUserRelationService {

    @Autowired
    private UserClient userClient;

    @Autowired
    private JobService jobService;

    private void addUserName(List<JobUserRelation> jobs) throws Exception {
        Map<Integer, String> userNameMap = new HashMap<>(jobs.size());
        for (JobUserRelation job : jobs) {
            if (job.getUserId() != null) userNameMap.put(job.getUserId(), "");
        }
        for (Integer id : userNameMap.keySet()) {
            userNameMap.put(id, userClient.getNameById(id).getData());
        }
        for (int i = 0; i < jobs.size(); i++) {
            JobUserRelation job = jobs.get(i);
            JobUserRelationVO jobVO;
            if (job instanceof JobUserRelationVO) {
                jobVO = (JobUserRelationVO) job;
            } else {
                jobVO = BeanUtils.copyFromFather(job, new JobUserRelationVO());
            }
            if (job.getUserId() != null) jobVO.setUserName(userNameMap.get(job.getUserId()));
            jobs.set(i, jobVO);
        }
    }

    @Override
    public ResultVO sendResume(Integer jobId, Integer userId, Integer resumeId) {
        ResultVO result = new ResultVO();
        if (userClient.isHunter(userId)) {
            JobUserRelation jobUserRelation = new JobUserRelation();
            jobUserRelation.setResumeId(resumeId);
            jobUserRelation.setJobId(jobId);
            jobUserRelation.setProgress("0");
            jobUserRelation.setStatus("0");
            jobUserRelation.setCreateDate(DateTimeUtils.nowDateFormat());
            jobUserRelation.setUserId(userId);
            if (baseMapper.insert(jobUserRelation) == 1) {
                result.setResponseCode(ResponseCode.SUCCESS);
            } else {
                result.setResponseCode(ResponseCode.FAIL);
            }
        } else {
            result.setResponseCode(ResponseCode.NO_OPERATION_PERMISSION);
        }
        return result;
    }

    @Override
    public ResultVO<List<JobUserRelation>> queryMine(Integer userId) {
        ResultVO<List<JobUserRelation>> result = new ResultVO<>();
        result.setData(baseMapper.selectList(new QueryWrapper<JobUserRelation>()
                .eq("user_id",userId)
                .orderByDesc("create_date")));
        return result;
    }

    @Override
    public ResultVO<Page<JobUserRelation>> queryPage(Integer userId, JobUserRelation condition, Integer pageSize, Integer pageNum) throws Exception {
        ResultVO<Page<JobUserRelation>> result = new ResultVO<>();
        Page<JobUserRelation> page = new Page<>(pageNum, pageSize);
        page = baseMapper.selectPage(page, new QueryWrapper<JobUserRelation>()
                .eq("job_id", condition.getJobId())
                .eq(!StringUtils.isEmpty(condition.getStatus()), "status", condition.getStatus())
                .eq(!StringUtils.isEmpty(condition.getProgress()), "progress", condition.getProgress()));
        addUserName(page.getRecords());
        result.setData(page);
        return result;
    }

    @Override
    public ResultVO passApplication(Integer userId, Integer id) {
        ResultVO result = new ResultVO();
        JobUserRelation jobUserRelation = baseMapper.selectOne(new QueryWrapper<JobUserRelation>()
                .select("id", "status", "progress")
                .eq("user_id", userId)
                .eq("id", id));
        if (jobUserRelation == null) {
            result.setResponseCode(ResponseCode.PARAMETER_ILLEGAL);
        } else if ("0".equals(jobUserRelation.getStatus())) {
            jobUserRelation.setStatus("1");
            jobUserRelation.setProgress("1");
            baseMapper.updateById(jobUserRelation);
            result.setResponseCode(ResponseCode.SUCCESS);
        } else {
            result.setResponseCode(ResponseCode.NO_OPERATION_PERMISSION);
        }
        return result;
    }

    @Override
    public ResultVO refuseApplication(Integer userId, Integer id) {
        ResultVO result = new ResultVO();
        JobUserRelation jobUserRelation = baseMapper.selectOne(new QueryWrapper<JobUserRelation>()
                .select("id", "status")
                .eq("user_id", userId)
                .eq("id", id));
        if (jobUserRelation == null) {
            result.setResponseCode(ResponseCode.PARAMETER_ILLEGAL);
        } else if ("0".equals(jobUserRelation.getStatus())) {
            jobUserRelation.setStatus("2");
            baseMapper.updateById(jobUserRelation);
            result.setResponseCode(ResponseCode.SUCCESS);
        } else {
            result.setResponseCode(ResponseCode.NO_OPERATION_PERMISSION);
        }
        return result;
    }

    @Override
    public ResultVO nextProgress(Integer userId, Integer id) {
        ResultVO result = new ResultVO();
        JobUserRelation jobUserRelation = baseMapper.selectOne(new QueryWrapper<JobUserRelation>()
                .select("id", "status", "progress", "job_id")
                .eq("user_id", userId)
                .eq("id", id));
        if (jobUserRelation == null) {
            result.setResponseCode(ResponseCode.PARAMETER_ILLEGAL);
        } else if ("1".equals(jobUserRelation.getStatus())) {
            switch (jobUserRelation.getProgress()) {
                case "1" : jobUserRelation.setProgress("2");break;
                case "2" :
                    jobUserRelation.setProgress("3");
                    jobService.addAuditionNum(jobUserRelation.getJobId());
                    break;
                default:
                    result.setResponseCode(ResponseCode.INVALID_OPERATION);
                    return result;
            }
            baseMapper.updateById(jobUserRelation);
            result.setResponseCode(ResponseCode.SUCCESS);
        } else {
            result.setResponseCode(ResponseCode.NO_OPERATION_PERMISSION);
        }
        return result;
    }

    @Override
    public ResultVO progressFail(Integer userId, Integer id) {
        ResultVO result = new ResultVO();
        JobUserRelation jobUserRelation = baseMapper.selectOne(new QueryWrapper<JobUserRelation>()
                .select("id", "status", "progress")
                .eq("user_id", userId)
                .eq("id", id));
        if (jobUserRelation == null) {
            result.setResponseCode(ResponseCode.PARAMETER_ILLEGAL);
        } else if ("0".equals(jobUserRelation.getStatus())
                && !"3".equals(jobUserRelation.getProgress())
                && !"4".equals(jobUserRelation.getProgress())) {
            jobUserRelation.setProgress("4");
            baseMapper.updateById(jobUserRelation);
            result.setResponseCode(ResponseCode.SUCCESS);
        } else {
            result.setResponseCode(ResponseCode.NO_OPERATION_PERMISSION);
        }
        return result;
    }
}
