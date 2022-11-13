package com.study.hunting;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(value = "com.study.hunting.dao")
public class UserSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserSystemApplication.class, args);
    }

}
