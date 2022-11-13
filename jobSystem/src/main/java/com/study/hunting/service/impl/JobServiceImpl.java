package com.study.hunting.service.impl;

import com.study.hunting.domain.Job;
import com.study.hunting.dao.JobMapper;
import com.study.hunting.service.JobService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
