package com.study.hunting.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(name = "baseInfoSystem", path = "/city")
public interface CityClient {

    @GetMapping("/global/all")
    Map<Integer, String> getCityInfo();

    @GetMapping("/global")
    String getCityNameById(@RequestParam("cityId") Integer cityId);
}
