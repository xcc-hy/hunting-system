package com.study.hunting.client;

import com.study.hunting.pojo.TypeDetailPOJO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(name = "baseInfoSystem", path = "/positionDetail")
public interface PositionClient {

    @GetMapping("/global/all")
    Map<Integer, TypeDetailPOJO> getIndustryInfo();

    @GetMapping("/global/detail/all")
    Map<Integer, String> getIndustryDetailInfo();

    @GetMapping("/global")
    String getIndustryNameById(@RequestParam("industryId") Integer industryId);
}
