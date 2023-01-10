package com.study.hunting.controller;


import com.study.hunting.domain.Job;
import com.study.hunting.enums.ResponseCode;
import com.study.hunting.service.JobService;
import com.study.hunting.service.UserStarService;
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
@RequestMapping("/userStar")
public class UserStarController {

    @Autowired
    private UserStarService userStarService;

    @Autowired
    private JobService jobService;

    @GetMapping("/queryMine")
    public ResultVO<List<Job>> queryMine(@RequestHeader Integer userId) throws Exception {
        return jobService.getByIds(userStarService.queryMyStar(userId));
    }

    @PostMapping("/add/{jobId}")
    public ResultVO addStar(@RequestHeader Integer userId, @PathVariable Integer jobId) {
        if (!jobService.exist(jobId)) {
            ResultVO result = new ResultVO();
            result.setResponseCode(ResponseCode.PARAMETER_ILLEGAL);
            return result;
        }
        return userStarService.addStar(userId, jobId);
    }

    @DeleteMapping("/remove/{jobId}")
    public ResultVO removeStar(@RequestHeader Integer userId, @PathVariable Integer jobId) {
        return userStarService.removeStar(userId, jobId);
    }

}
