package com.study.hunting;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import sun.security.pkcs11.P11Util;

import java.util.Collections;

@SpringBootApplication
@MapperScan("com.study.hunting.dao")
@EnableFeignClients
public class JobSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(JobSystemApplication.class, args);
    }

    /**
     * mybatisplus的分页查询插件，不设置无法分页
     * @return
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        PaginationInnerInterceptor paginationInnerInterceptor = new PaginationInnerInterceptor();
        paginationInnerInterceptor.setMaxLimit(100L);
        paginationInnerInterceptor.setDbType(DbType.MYSQL);
        paginationInnerInterceptor.setOptimizeJoin(true);
        paginationInnerInterceptor.setOverflow(true);
        MybatisPlusInterceptor mybatisPlusInterceptor = new MybatisPlusInterceptor();
        mybatisPlusInterceptor.setInterceptors(Collections.singletonList(paginationInnerInterceptor));
        return mybatisPlusInterceptor;
    }

}
