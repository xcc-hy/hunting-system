package com.study.hunting.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.study.hunting.domain.Job;
import com.baomidou.mybatisplus.extension.service.IService;
import com.study.hunting.vo.ResultVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xcc
 * @since 2022-10-02
 */
public interface JobService extends IService<Job> {

    ResultVO<Page<Job>> getJobListByOwnerId(String ownerId, Integer pageSize, Integer pageNum);

    ResultVO<Page<Job>> getJobList(Job jobCondition, Integer pageSize, Integer pageNum);
}
