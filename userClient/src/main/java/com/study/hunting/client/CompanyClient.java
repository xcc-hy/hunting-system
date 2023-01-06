package com.study.hunting.client;

import com.study.hunting.domain.Company;
import com.study.hunting.vo.ResultVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "userSystem", path = "/company")
public interface CompanyClient {

    @GetMapping("/selectByFullName/{fullName}")
    ResultVO<Company> selectByFullName(@PathVariable("fullName") String fullName);

    @GetMapping("/selectById/{id}")
    ResultVO<Company> selectById(@PathVariable("id") Integer id);

    @GetMapping("/fuzzy/{fullName}")
    ResultVO<List<String>> selectByFuzzy(@PathVariable("fullName") String fullName);

    @PostMapping(value = "/secret/create/{userId}", consumes = "application/json")
    Integer create(@PathVariable("userId") Integer userId, @RequestBody Company company);

    @GetMapping("/selectIdByFullName/{fullName}")
    Integer selectIdByFullName(@PathVariable("fullName") String fullName);

    @GetMapping("/selectIdByOwnerId/{userId}")
    ResultVO<List<Integer>> selectIdByOwnerId(@PathVariable("userId") Integer userId);

    @GetMapping("/getNameById/{id}")
    ResultVO<String> getNameById(@PathVariable("id") Integer id);
}
