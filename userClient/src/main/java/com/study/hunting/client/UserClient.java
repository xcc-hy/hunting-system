package com.study.hunting.client;

import com.study.hunting.vo.ResultVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "userSystem", path = "/user")
public interface UserClient {

    @GetMapping("/secret/isManager/{userId}")
    Boolean isManager(@PathVariable("userId") Integer userId);

    @GetMapping("/isEnterprise/{userId}")
    ResultVO<Boolean> isEnterprise(@PathVariable("userId") Integer userId);

    @PutMapping("/secret/upgrade/enterprise/{id}")
    Boolean upgradeEnterprise(@PathVariable("id") Integer id,
                              @RequestHeader("userId") Integer userId,
                              @RequestParam("isManager") Boolean isManager,
                              @RequestParam("companyId") Integer companyId);

    @GetMapping("/getCompanyIdById/{userId}")
    ResultVO<Integer> getCompanyIdById(@PathVariable("userId") Integer userId);

    @GetMapping("/getNameById/{userId}")
    ResultVO<String> getNameById(@PathVariable("userId") Integer userId);
}
