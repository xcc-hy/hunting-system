package com.study.hunting;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@MapperScan(value = "com.study.hunting.dao")
@EnableDiscoveryClient
@EnableFeignClients
public class UserSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserSystemApplication.class, args);
    }

}
