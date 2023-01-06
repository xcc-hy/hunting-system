package com.study.hunting.service;

import com.study.hunting.domain.Job;
import com.study.hunting.domain.NewJobApplication;
import com.baomidou.mybatisplus.extension.service.IService;
import com.study.hunting.vo.ResultVO;
import io.seata.core.exception.TransactionException;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xcc
 * @since 2022-12-30
 */
public interface NewJobApplicationService extends IService<NewJobApplication> {

    ResultVO createApplication(Integer userId, Job job) throws TransactionException;

    ResultVO passApplication(Integer id, Integer userId, String reviewReason) throws TransactionException;

    ResultVO refuseApplication(Integer id, Integer userId, String reviewReason) throws TransactionException;

    ResultVO<List<NewJobApplication>> queryByJobId(Integer userId, Integer jobId) throws Exception;

    ResultVO reapplyJob(Integer userId, Integer jobId);

    ResultVO freezeApplication(Integer jobId, Integer userId, String reviewReason) throws TransactionException;

    ResultVO recallApplication(Integer jobId, Integer userId);
}
