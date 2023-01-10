package com.study.hunting.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.study.hunting.client.CompanyClient;
import com.study.hunting.client.UserClient;
import com.study.hunting.domain.Job;
import com.study.hunting.dao.JobMapper;
import com.study.hunting.domain.JobUserRelation;
import com.study.hunting.enums.ResponseCode;
import com.study.hunting.service.JobService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.study.hunting.util.BeanUtils;
import com.study.hunting.util.DateTimeUtils;
import com.study.hunting.vo.JobUserRelationVO;
import com.study.hunting.vo.JobVO;
import com.study.hunting.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xcc
 * @since 2022-10-02
 */
@Service
public class JobServiceImpl extends ServiceImpl<JobMapper, Job> implements JobService {

    @Autowired
    private UserClient userClient;

    @Autowired
    private CompanyClient companyClient;

    private void addOwnerName(List<Job> jobs) throws Exception {
        Map<Integer, String> userNameMap = new HashMap<>(jobs.size());
        for (Job job : jobs) {
            if (job.getOwnerId() != null) userNameMap.put(job.getOwnerId(), "");
        }
        for (Integer id : userNameMap.keySet()) {
            userNameMap.put(id, userClient.getNameById(id).getData());
        }
        for (int i = 0; i < jobs.size(); i++) {
            Job job = jobs.get(i);
            JobVO jobVO;
            if (job instanceof JobVO) {
                jobVO = (JobVO) job;
            } else {
                jobVO = BeanUtils.copyFromFather(job, new JobVO());
            }
            if (job.getOwnerId() != null) jobVO.setOwnerName(userNameMap.get(job.getOwnerId()));
            jobs.set(i, jobVO);
        }
    }
    private JobVO addOwnerName(Job job) throws Exception {
        JobVO jobVO;
        if (job instanceof JobVO) {
            jobVO = (JobVO) job;
        } else {
            jobVO = BeanUtils.copyFromFather(job, new JobVO());
        }
        if (jobVO.getOwnerId() != null) jobVO.setOwnerName(userClient.getNameById(jobVO.getOwnerId()).getData());
        return jobVO;
    }

    private void addCompanyName(List<Job> jobs) throws Exception {
        Map<Integer, String> companyNameMap = new HashMap<>(jobs.size());
        for (Job job : jobs) {
            if (job.getCompanyId() != null) companyNameMap.put(job.getCompanyId(), "");
        }
        for (Integer id : companyNameMap.keySet()) {
            companyNameMap.put(id, companyClient.getNameById(id).getData());
        }
        for (int i = 0; i < jobs.size(); i++) {
            Job job = jobs.get(i);
            JobVO jobVO;
            if (job instanceof JobVO) {
                jobVO = (JobVO) job;
            } else {
                jobVO = BeanUtils.copyFromFather(job, new JobVO());
            }
            if (job.getCompanyId() != null) jobVO.setCompanyName(companyNameMap.get(job.getCompanyId()));
            jobs.set(i, jobVO);
        }
    }
    private JobVO addCompanyName(Job job) throws Exception {
        JobVO jobVO;
        if (job instanceof JobVO) {
            jobVO = (JobVO) job;
        } else {
            jobVO = BeanUtils.copyFromFather(job, new JobVO());
        }
        if (jobVO.getCompanyName() != null) jobVO.setCompanyName(companyClient.getNameById(jobVO.getCompanyId()).getData());
        return jobVO;
    }

    @Override
    public void addJob(List<JobUserRelation> data) throws Exception {
        for (int i = 0; i < data.size(); i++) {
            Job job = baseMapper.selectById(data.get(i).getJobId());
            job.setAuditionNum(null);
            job.setDeliverNum(null);
            job = addOwnerName(job);
            job = addCompanyName(job);
            JobUserRelationVO jobUserRelationVO = BeanUtils.copyFromFather(data.get(i), new JobUserRelationVO());
            jobUserRelationVO.setJob(job);
            data.set(i, jobUserRelationVO);
        }
    }

    @Override
    public void addDeliverNum(Integer jobId) {
        Job job = baseMapper.selectOne(new QueryWrapper<Job>().select("deliver_num").eq("id", jobId));
        job.setDeliverNum(job.getDeliverNum() + 1);
        baseMapper.updateById(job);
    }

    @Override
    public boolean isOwner(Integer userId, Integer jobId) {
        return baseMapper.selectCount(new QueryWrapper<Job>().eq("owner_id",userId).eq("id", jobId)) > 0;
    }

    @Override
    public ResultVO<List<Job>> getPublishedJob(Integer userId) {
        ResultVO<List<Job>> result = new ResultVO<>();
        List<Job> jobs = baseMapper.selectList(new QueryWrapper<Job>()
                .select("id", "name")
                .eq("owner_id", userId)
                .in("status", "1", "3"));
        result.setData(jobs);
        return result;
    }

    @Override
    public void addAuditionNum(Integer jobId) {
        Job job = baseMapper.selectOne(new QueryWrapper<Job>().select("audition_num").eq("id", jobId));
        job.setAuditionNum(job.getAuditionNum() + 1);
        baseMapper.updateById(job);
    }

    @Override
    public ResultVO<Page<Job>> getJobListByOwnerId(Integer ownerId, Integer userId, Job jobCondition, Integer pageSize, Integer pageNum) {
        ResultVO<Page<Job>> result = new ResultVO<>();
        if (userId == null) {
            result.setResponseCode(ResponseCode.FAIL);
            return result;
        }
        Page<Job> page = new Page<>(pageNum, pageSize);
        QueryWrapper<Job> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("owner_id", ownerId).orderByDesc("create_time");
        if (jobCondition != null) {
            queryWrapper.eq(!StringUtils.isEmpty(jobCondition.getIsPractice()),"is_practice", jobCondition.getIsPractice())
                    .eq(!StringUtils.isEmpty(jobCondition.getEducation()), "education", jobCondition.getEducation())
                    .ge(jobCondition.getStartPrice() != null, "start_price", jobCondition.getStartPrice())
                    .le(jobCondition.getEndPrice() != null, "end_price", jobCondition.getEndPrice())
                    .eq(jobCondition.getLocationId() != null, "location_id", jobCondition.getLocationId())
                    .eq(jobCondition.getPositionId() != null, "position_id", jobCondition.getPositionId())
                    .eq(jobCondition.getIndustryId() != null, "industry_id", jobCondition.getIndustryId())
                    .ge(jobCondition.getWorkYear() != null, "work_year", jobCondition.getWorkYear());
        }
        if (!userId.equals(ownerId)) {
            queryWrapper.eq("status", "1"); // 非本人只能查看审核通过的职位信息
        } else {
            queryWrapper.eq(!StringUtils.isEmpty(jobCondition.getStatus()), "status", jobCondition.getStatus());
        }
        page = baseMapper.selectPage(page, queryWrapper);
        if (page == null) {
            result.setResponseCode(ResponseCode.PARAMETER_ILLEGAL);
        } else {
            if (!userId.equals(ownerId)) {
                page.getRecords().forEach(o -> {
                    o.setDeliverNum(null);
                    o.setAuditionNum(null); // 将私密信息隐藏
                });
            }
            result.setData(page);
        }
        return result;
    }

    @Override
    public ResultVO<Page<Job>> getJobList(Job jobCondition, Integer pageSize, Integer pageNum) throws Exception {
        ResultVO<Page<Job>> result = new ResultVO<>();
        Page<Job> page = new Page<>(pageNum, pageSize);
        QueryWrapper<Job> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("status", "1").orderByDesc("create_time");
        if (jobCondition != null) {
            queryWrapper.eq(!StringUtils.isEmpty(jobCondition.getIsPractice()),"is_practice", jobCondition.getIsPractice())
                    .eq(!StringUtils.isEmpty(jobCondition.getEducation()), "education", jobCondition.getEducation())
                    .ge(jobCondition.getStartPrice() != null, "start_price", jobCondition.getStartPrice())
                    .le(jobCondition.getEndPrice() != null, "end_price", jobCondition.getEndPrice())
                    .eq(jobCondition.getLocationId() != null, "location_id", jobCondition.getLocationId())
                    .eq(jobCondition.getPositionId() != null, "position_id", jobCondition.getPositionId())
                    .eq(jobCondition.getIndustryId() != null, "industry_id", jobCondition.getIndustryId())
                    .ge(jobCondition.getWorkYear() != null, "work_year", jobCondition.getWorkYear());
        }
        page = baseMapper.selectPage(page, queryWrapper);
        page.getRecords().forEach(o -> {
            o.setDeliverNum(null);
            o.setAuditionNum(null); // 将私密信息隐藏
        });
        addCompanyName(page.getRecords());
        addOwnerName(page.getRecords());
        result.setData(page);
        return result;
    }

    @Override
    public ResultVO<Page<Job>> getJobList2(Job jobCondition, Integer pageSize, Integer pageNum, Integer userId) throws Exception {
        ResultVO<Page<Job>> result = new ResultVO<>();
        if (!userClient.isManager(userId)) {
            result.setResponseCode(ResponseCode.NO_OPERATION_PERMISSION);
            return result;
        }
        Page<Job> page = new Page<>(pageNum, pageSize);
        QueryWrapper<Job> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("create_time");
        if (jobCondition != null) {
            queryWrapper.eq(!StringUtils.isEmpty(jobCondition.getIsPractice()),"is_practice", jobCondition.getIsPractice())
                    .eq(!StringUtils.isEmpty(jobCondition.getEducation()), "education", jobCondition.getEducation())
                    .ge(jobCondition.getStartPrice() != null, "start_price", jobCondition.getStartPrice())
                    .le(jobCondition.getEndPrice() != null, "end_price", jobCondition.getEndPrice())
                    .eq(jobCondition.getLocationId() != null, "location_id", jobCondition.getLocationId())
                    .eq(jobCondition.getPositionId() != null, "position_id", jobCondition.getPositionId())
                    .eq(jobCondition.getIndustryId() != null, "industry_id", jobCondition.getIndustryId())
                    .ge(jobCondition.getWorkYear() != null, "work_year", jobCondition.getWorkYear())
                    .eq(!StringUtils.isEmpty(jobCondition.getStatus()), "status", jobCondition.getStatus());
        }
        page = baseMapper.selectPage(page, queryWrapper);
        page.getRecords().forEach(o -> {
            o.setDeliverNum(null);
            o.setAuditionNum(null); // 将私密信息隐藏
        });
        addOwnerName(page.getRecords());
        addCompanyName(page.getRecords());
        result.setData(page);
        return result;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public ResultVO<Integer> createJob(Integer userId, Job job) {
        ResultVO<Integer> result = new ResultVO<>();
        if (!userClient.isEnterprise(userId).getData()) {
            result.setResponseCode(ResponseCode.NO_OPERATION_PERMISSION);
        } else {
            job.setId(null);
            job.setAuditionNum(0);
            job.setDeliverNum(0);
            job.setCreateTime(DateTimeUtils.nowTimeFormat());
            job.setOwnerId(userId);
            job.setCompanyId(userClient.getCompanyIdById(userId).getData());
            job.setStatus("0");
            baseMapper.insert(job);
            result.setData(job.getId());
        }
        return result;
    }

    @Override
    @Transactional
    public ResultVO passJob(Integer id, Integer userId) {
        ResultVO result = new ResultVO();
        Job job = baseMapper.selectById(id);
        if (job == null) {
            result.setResponseCode(ResponseCode.PARAMETER_ILLEGAL);
            return result;
        }
        if (userClient.isManager(userId) && "0".equals(job.getStatus())) {
            job.setStatus("1");
            baseMapper.updateById(job);
            result.setResponseCode(ResponseCode.SUCCESS);
        } else {
            result.setResponseCode(ResponseCode.NO_OPERATION_PERMISSION);
        }
        return result;
    }

    @Override
    @Transactional
    public ResultVO refuseJob(Integer id, Integer userId) {
        ResultVO result = new ResultVO();
        Job job = baseMapper.selectById(id);
        if (job == null) {
            result.setResponseCode(ResponseCode.PARAMETER_ILLEGAL);
            return result;
        }
        if (userClient.isManager(userId) && "0".equals(job.getStatus())) {
            job.setStatus("2");
            baseMapper.updateById(job);
            result.setResponseCode(ResponseCode.SUCCESS);
        } else {
            result.setResponseCode(ResponseCode.NO_OPERATION_PERMISSION);
        }
        return result;
    }

    @Override
    @Transactional
    public ResultVO freezeJob(Integer id, Integer userId) {
        ResultVO result = new ResultVO();
        Job job = baseMapper.selectById(id);
        if (job == null) {
            result.setResponseCode(ResponseCode.PARAMETER_ILLEGAL);
            return result;
        }
        if (userId.equals(job.getOwnerId()) && "1".equals(job.getStatus())) {
            job.setStatus("3");
            baseMapper.updateById(job);
            result.setResponseCode(ResponseCode.SUCCESS);
        } else {
            result.setResponseCode(ResponseCode.NO_OPERATION_PERMISSION);
        }
        return result;
    }

    @Override
    @Transactional
    public ResultVO freezeJob2(Integer id, Integer userId) {
        ResultVO result = new ResultVO();
        Job job = baseMapper.selectById(id);
        if (job == null) {
            result.setResponseCode(ResponseCode.PARAMETER_ILLEGAL);
            return result;
        }
        if (userClient.isManager(userId) && ("1".equals(job.getStatus()) || "3".equals(job.getStatus()))) {
            job.setStatus("4");
            baseMapper.updateById(job);
            result.setResponseCode(ResponseCode.SUCCESS);
        } else {
            result.setResponseCode(ResponseCode.NO_OPERATION_PERMISSION);
        }
        return result;
    }

    @Override
    public ResultVO recallJob(Integer id, Integer userId) {
        ResultVO result = new ResultVO();
        Job job = baseMapper.selectById(id);
        if (job == null) {
            result.setResponseCode(ResponseCode.PARAMETER_ILLEGAL);
            return result;
        }
        if (userId.equals(job.getOwnerId()) && "0".equals(job.getStatus())) {
            job.setStatus("5");
            baseMapper.updateById(job);
            result.setResponseCode(ResponseCode.SUCCESS);
        } else {
            result.setResponseCode(ResponseCode.NO_OPERATION_PERMISSION);
        }
        return result;
    }

    @Override
    public ResultVO<List<Job>> getByIds(List<Integer> jobIds) throws Exception {
        ResultVO<List<Job>> result = new ResultVO<>();
        List<Job> jobs = baseMapper.selectList(new QueryWrapper<Job>().in("id", jobIds));
        addOwnerName(jobs);
        addCompanyName(jobs);
        return result;
    }

    @Override
    public boolean exist(Integer jobId) {
        return baseMapper.selectCount(new QueryWrapper<Job>().eq("id", jobId).eq("status", "1")) > 0;
    }

    @Override
    @Transactional
    public ResultVO reapply(Integer jobId, Integer userId) {
        ResultVO result = new ResultVO();
        Job job = baseMapper.selectById(jobId);
        if (job == null || !userId.equals(job.getOwnerId())) {
            result.setResponseCode(ResponseCode.NO_OPERATION_PERMISSION);
        } else {
            job.setStatus("0");
            baseMapper.updateById(job);
            result.setResponseCode(ResponseCode.SUCCESS);
        }
        return result;
    }

    @Override
    public ResultVO editJob(Integer jobId, Integer userId, Job job) {
        ResultVO result = new ResultVO();
        Job curJob = baseMapper.selectById(jobId);
        if (job == null || !userId.equals(job.getOwnerId())) {
            result.setResponseCode(ResponseCode.NO_OPERATION_PERMISSION);
        } else if ("2".equals(curJob.getStatus()) || "4".equals(curJob.getStatus()) || "5".equals(curJob.getStatus())) {
            curJob.setName(job.getName());
            curJob.setLocationId(job.getLocationId());
            curJob.setPositionId(job.getPositionId());
            curJob.setIndustryId(job.getIndustryId());
            curJob.setEducation(job.getEducation());
            curJob.setStartPrice(job.getStartPrice());
            curJob.setEndPrice(job.getEndPrice());
            curJob.setIsPractice(job.getIsPractice());
            curJob.setIntroduce(job.getIntroduce());
            curJob.setWorkYear(job.getWorkYear());
            curJob.setRequiredNum(job.getRequiredNum());
            curJob.setAddress(job.getAddress());
            baseMapper.updateById(curJob);
            result.setResponseCode(ResponseCode.SUCCESS);
        } else {
            result.setResponseCode(ResponseCode.INVALID_OPERATION);
        }
        return result;
    }

    @Override
    public ResultVO unfreezeJob(Integer id, Integer userId) {
        ResultVO result = new ResultVO();
        Job job = baseMapper.selectById(id);
        if (job == null) {
            result.setResponseCode(ResponseCode.PARAMETER_ILLEGAL);
            return result;
        }
        if (userId.equals(job.getOwnerId()) && "3".equals(job.getStatus())) {
            job.setStatus("1");
            baseMapper.updateById(job);
            result.setResponseCode(ResponseCode.SUCCESS);
        } else {
            result.setResponseCode(ResponseCode.NO_OPERATION_PERMISSION);
        }
        return result;
    }


}
