package com.study.hunting.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.study.hunting.domain.Job;
import com.study.hunting.enums.ResponseCode;
import com.study.hunting.service.JobService;
import com.study.hunting.vo.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public ResultVO<Page<Job>> getJobList(@PathVariable Integer pageSize, @PathVariable Integer pageNum, Job jobCondition) throws Exception {
        return jobService.getJobList(jobCondition, pageSize, pageNum);
    }

    @GetMapping("/manager/list/{pageSize}/{pageNum}")
    @ApiOperation(value = "条件查询各个职位信息")
    public ResultVO<Page<Job>> getJobList2(@PathVariable Integer pageSize, @PathVariable Integer pageNum, Job jobCondition, @RequestHeader Integer userId) throws Exception {
        return jobService.getJobList2(jobCondition, pageSize, pageNum, userId);
    }

    @GetMapping("/listByUserId")
    public ResultVO<List<Job>> getPublishedJob(@RequestHeader Integer userId) {
        return jobService.getPublishedJob(userId);
    }

    @PutMapping("/secret/manager/freeze/{id}")
    @ApiOperation("冻结该工作，求职者无法获取到该工作")
    public ResultVO freezeJob2(@PathVariable Integer id,@RequestHeader Integer userId) {
        return jobService.freezeJob2(id, userId);
    }

    @PutMapping("/secret/recall/{id}")
    @ApiOperation("撤回申请")
    public ResultVO recallJob(@PathVariable Integer id,@RequestHeader Integer userId) {
        return jobService.recallJob(id, userId);
    }

    @GetMapping("/detail/{jobId}")
    @ApiOperation(value = "获取工作的详细信息")
    public ResultVO<Job> getJobDetailById(@PathVariable Integer jobId) {
        Job job = jobService.getById(jobId);
        ResultVO<Job> result = new ResultVO<>();
        if (job != null) {
            result.setData(job);
        } else {
            result.setResponseCode(ResponseCode.PRIMARY_ID_ERROR);
        }
        return result;
    }

    @PutMapping("/secret/reapply/{jobId}")
    @ApiOperation("工作重新申请")
    public ResultVO reapply(@PathVariable Integer jobId, @RequestHeader Integer userId) {
        return jobService.reapply(jobId, userId);
    }

    @PutMapping("/edit/{jobId}")
    @ApiOperation("修改工作内容")
    public ResultVO editJob(@PathVariable Integer jobId, @RequestHeader Integer userId, Job job) {
        return jobService.editJob(jobId, userId, job);
    }

    @GetMapping("/list/{ownerId}")
    @ApiOperation(value = "获取来自某一个hr投放的所有职位信息")
    public ResultVO<Page<Job>> getJobListByOwnerId(@PathVariable Integer ownerId,
                                                   @RequestHeader Integer userId,
                                                   Integer pageSize,
                                                   Integer pageNum, Job jobCondition) {
        return jobService.getJobListByOwnerId(ownerId, userId, jobCondition, pageSize, pageNum);
    }

    @PostMapping("/secret/create/{userId}")
    @ApiOperation("创建新工作")
    public ResultVO<Integer> createJob(@PathVariable Integer userId, @RequestBody Job job) {
        return jobService.createJob(userId, job);
    }

    @PutMapping("/secret/pass/{id}")
    @ApiOperation("工作审核通过")
    public ResultVO passJob(@PathVariable Integer id,@RequestHeader Integer userId) {
        return jobService.passJob(id, userId);
    }

    @PutMapping("/secret/refuse/{id}")
    @ApiOperation("工作审核不通过")
    public ResultVO refuseJob(@PathVariable Integer id,@RequestHeader Integer userId) {
        return jobService.refuseJob(id, userId);
    }

    @PutMapping("/freeze/{id}")
    @ApiOperation("冻结该工作，求职者无法获取到该工作")
    public ResultVO freezeJob(@PathVariable Integer id,@RequestHeader Integer userId) {
        return jobService.freezeJob(id, userId);
    }

    @PutMapping("/unfreeze/{id}")
    @ApiOperation("解冻该工作，求职者可以获取到该工作")
    public ResultVO unfreezeJob(@PathVariable Integer id,@RequestHeader Integer userId) {
        return jobService.unfreezeJob(id, userId);
    }
}
