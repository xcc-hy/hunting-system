package com.study.hunting.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.study.hunting.domain.Job;
import com.baomidou.mybatisplus.extension.service.IService;
import com.study.hunting.vo.ResultVO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xcc
 * @since 2022-10-02
 */
public interface JobService extends IService<Job> {

    ResultVO<Page<Job>> getJobListByOwnerId(Integer ownerId, Integer userId, Job jobCondition, Integer pageSize, Integer pageNum);

    ResultVO<Page<Job>> getJobList(Job jobCondition, Integer pageSize, Integer pageNum) throws Exception;

    ResultVO<Integer> createJob(Integer userId, Job job);

    ResultVO passJob(Integer id, Integer userId);

    ResultVO refuseJob(Integer id, Integer userId);

    ResultVO freezeJob(Integer id, Integer userId);

    ResultVO reapply(Integer jobId, Integer userId);

    ResultVO editJob(Integer jobId, Integer userId, Job job);

    ResultVO unfreezeJob(Integer id, Integer userId);

    ResultVO<Page<Job>> getJobList2(Job jobCondition, Integer pageSize, Integer pageNum, Integer userId) throws Exception;

    ResultVO freezeJob2(Integer id, Integer userId);

    ResultVO recallJob(Integer id, Integer userId);
}
