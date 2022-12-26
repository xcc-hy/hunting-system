package com.study.hunting;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@MapperScan(value = "com.study.hunting.dao")
@EnableDiscoveryClient // 能够让注册中心能够发现,扫描到该服务
public class BaseInfoSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(BaseInfoSystemApplication.class, args);
    }

}
