package com.study.hunting.controller;


import com.study.hunting.domain.Job;
import com.study.hunting.domain.NewJobApplication;
import com.study.hunting.service.NewCompanyApplicationService;
import com.study.hunting.service.NewJobApplicationService;
import com.study.hunting.vo.ResultVO;
import io.seata.core.exception.TransactionException;
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
 * @since 2022-12-30
 */
@RestController
@RequestMapping("/newJobApplication")
public class NewJobApplicationController {

    @Autowired
    private NewJobApplicationService newJobApplicationService;

    @GetMapping("/query/{jobId}")
    @ApiOperation("查询某工作的所有申请流程")
    public ResultVO<List<NewJobApplication>> queryByJobId(@RequestHeader Integer userId, @PathVariable Integer jobId) throws Exception {
        return newJobApplicationService.queryByJobId(userId, jobId);
    }

    @PostMapping("/create")
    @ApiOperation("新建工作")
    public ResultVO createApplication(@RequestHeader Integer userId, Job job) throws TransactionException {
        return newJobApplicationService.createApplication(userId, job);
    }

    @PostMapping("/reapply/{jobId}")
    @ApiOperation("已被拒绝的项目重新申请")
    public ResultVO reapplyJob(@RequestHeader Integer userId, @PathVariable Integer jobId) {
        return newJobApplicationService.reapplyJob(userId, jobId);
    }

    @PutMapping("/pass/{id}")
    public ResultVO passApplication(@PathVariable Integer id,@RequestHeader Integer userId, String reviewReason) throws TransactionException {
        return newJobApplicationService.passApplication(id, userId, reviewReason);
    }

    @PutMapping("/refuse/{id}")
    public ResultVO refuseApplication(@PathVariable Integer id,@RequestHeader Integer userId, String reviewReason) throws TransactionException {
        return newJobApplicationService.refuseApplication(id, userId, reviewReason);
    }

    @PostMapping("/freeze/{jobId}")
    public ResultVO freezeApplication(@PathVariable Integer jobId, @RequestHeader Integer userId, String reviewReason) throws TransactionException {
        return newJobApplicationService.freezeApplication(jobId, userId, reviewReason);
    }

    @DeleteMapping("/recall/{jobId}")
    public ResultVO recallApplication(@PathVariable Integer jobId, @RequestHeader Integer userId) {
        return newJobApplicationService.recallApplication(jobId, userId);
    }

}
