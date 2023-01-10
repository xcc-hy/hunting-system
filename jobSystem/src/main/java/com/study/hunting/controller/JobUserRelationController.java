package com.study.hunting.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.study.hunting.client.UserClient;
import com.study.hunting.domain.JobUserRelation;
import com.study.hunting.enums.ResponseCode;
import com.study.hunting.service.JobService;
import com.study.hunting.service.JobUserRelationService;
import com.study.hunting.vo.ResultVO;
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
@RequestMapping("/jobUserRelation")
public class JobUserRelationController {

    @Autowired
    private JobUserRelationService jobUserRelationService;

    @Autowired
    private JobService jobService;

    @PostMapping("/sendResume/{jobId}")
    public ResultVO sendResume(@PathVariable Integer jobId, @RequestHeader Integer userId, Integer resumeId) {
        ResultVO result;
        if (!jobService.exist(jobId)) {
            result = new ResultVO();
            result.setResponseCode(ResponseCode.PARAMETER_ILLEGAL);
            return result;
        }
        result = jobUserRelationService.sendResume(jobId, userId, resumeId);
        if (result.getCode() == ResponseCode.SUCCESS.getCode()) {
            jobService.addDeliverNum(jobId);
        }
        return result;
    }

    @GetMapping("/queryMine")
    public ResultVO<List<JobUserRelation>> queryMine(@RequestHeader Integer userId) throws Exception {
        ResultVO<List<JobUserRelation>> result = jobUserRelationService.queryMine(userId);
        if (result.getCode() == ResponseCode.SUCCESS.getCode()) {
            jobService.addJob(result.getData());
        }
        return result;
    }

    @GetMapping("/query/{pageSize}/{pageNum}")
    public ResultVO<Page<JobUserRelation>> queryPage(@RequestHeader Integer userId, JobUserRelation condition,
                                                     @PathVariable Integer pageSize, @PathVariable Integer pageNum) throws Exception {
        if (condition.getJobId() != null && jobService.isOwner(userId, condition.getJobId())) {
            return jobUserRelationService.queryPage(userId, condition, pageSize, pageNum);
        } else {
            ResultVO<Page<JobUserRelation>> result = new ResultVO();
            result.setResponseCode(ResponseCode.NO_OPERATION_PERMISSION);
            return result;
        }
    }

    @PutMapping("/passApplication/{id}")
    public ResultVO passApplication(@RequestHeader Integer userId, @PathVariable Integer id) {
        return jobUserRelationService.passApplication(userId, id);
    }

    @PutMapping("/refuseApplication/{id}")
    public ResultVO refuseApplication(@RequestHeader Integer userId, @PathVariable Integer id) {
        return jobUserRelationService.refuseApplication(userId, id);
    }

    @PutMapping("/nextProgress/{id}")
    public ResultVO nextProgress(@RequestHeader Integer userId, @PathVariable Integer id) {
        return jobUserRelationService.nextProgress(userId, id);
    }

    @PutMapping("/progressFail/{id}")
    public ResultVO progressFail(@RequestHeader Integer userId, @PathVariable Integer id) {
        return jobUserRelationService.progressFail(userId, id);
    }

}
