package com.study.hunting.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.study.hunting.domain.Job;
import com.study.hunting.dao.JobMapper;
import com.study.hunting.enums.ResponseCode;
import com.study.hunting.service.JobService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.study.hunting.vo.ResultVO;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public ResultVO<Page<Job>> getJobListByOwnerId(String ownerId, Integer pageSize, Integer pageNum) {
        ResultVO<Page<Job>> result = new ResultVO<>();
        Page<Job> page = new Page<>(pageNum, pageSize);
        QueryWrapper<Job> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("owner_id", ownerId);
        queryWrapper.orderByDesc("create_time");
        page = baseMapper.selectPage(page, queryWrapper);
        if (page == null) {
            result.setResponseCode(ResponseCode.PARAMETER_ILLEGAL);
        } else {
            result.setData(page);
        }
        return result;
    }

    @Override
    public ResultVO<Page<Job>> getJobList(Job jobCondition, Integer pageSize, Integer pageNum) {
        ResultVO<Page<Job>> result = new ResultVO<>();
        Page<Job> page = new Page<>(pageNum, pageSize);
        QueryWrapper<Job> queryWrapper = new QueryWrapper<>();
        if (jobCondition != null) {
            if (jobCondition.getIsPractice() != null) queryWrapper.eq("is_practice", jobCondition.getIsPractice());
        }
    }
}
