package com.study.hunting.client;

import com.study.hunting.domain.Job;
import com.study.hunting.vo.ResultVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "jobSystem", path = "/job")
public interface JobClient {

    @PostMapping(value = "/secret/create/{userId}", consumes = "application/json")
    ResultVO<Integer> createJob(@PathVariable("userId") Integer userId, @RequestBody Job job);

    @PutMapping("/secret/pass/{id}")
    ResultVO passJob(@PathVariable("id") Integer id, @RequestHeader("userId") Integer userId);

    @PutMapping("/secret/refuse/{id}")
    ResultVO refuseJob(@PathVariable("id") Integer id, @RequestHeader("userId") Integer userId);

    @PutMapping("/secret/reapply/{jobId}")
    ResultVO reapply(@PathVariable("jobId") Integer jobId, @RequestHeader("userId") Integer userId);

    @PutMapping("/secret/manager/freeze/{id}")
    ResultVO freezeJob2(@PathVariable("id") Integer id,@RequestHeader("userId") Integer userId);

    @PutMapping("/secret/recall/{id}")
    ResultVO recallJob(@PathVariable("id") Integer id,@RequestHeader("userId") Integer userId);

    @GetMapping("/detail/{jobId}")
    ResultVO<Job> getJobDetailById(@PathVariable("jobId") Integer jobId);
}
