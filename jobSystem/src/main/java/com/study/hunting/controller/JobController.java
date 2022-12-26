package com.study.hunting.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.study.hunting.domain.Job;
import com.study.hunting.enums.ResponseCode;
import com.study.hunting.service.JobService;
import com.study.hunting.vo.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xcc
 * @since 2022-10-02
 */
@RestController
@RequestMapping("/job")
@Api("工作相关信息管理")
public class JobController {
    @Autowired
    private JobService jobService;

    @GetMapping("/global/list/{pageSize}/{pageNum}")
    @ApiOperation(value = "条件查询各个职位信息")
    public ResultVO<Page<Job>> getJobList(@PathVariable Integer pageSize, @PathVariable Integer pageNum, Job jobCondition) {
        return jobService.getJobList(jobCondition, pageSize, pageNum);
    }

    @GetMapping("/detail/{jobId}")
    @ApiOperation(value = "获取工作的详细信息")
    public ResultVO<Job> getJobDetailById(@PathVariable String jobId) {
        Job job = jobService.getById(jobId);
        ResultVO<Job> result = new ResultVO<>();
        if (job != null) {
            result.setData(job);
        } else {
            result.setResponseCode(ResponseCode.PRIMARY_ID_ERROR);
        }
        return result;
    }

    @GetMapping("/list/{ownerId}")
    @ApiOperation(value = "获取来自某一个hr投放的所有职位信息")
    public ResultVO<Page<Job>> getJobListByOwnerId(@PathVariable String ownerId, Integer pageSize, Integer pageNum) {
        return jobService.getJobListByOwnerId(ownerId, pageSize, pageNum);
    }

}
