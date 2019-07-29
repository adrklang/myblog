package com.lhstack.myblog.oauth;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableDiscoveryClient
@EnableTransactionManagement
@MapperScan(basePackages = "com.lhstack.myblog.oauth.dao")
@ComponentScan(basePackages = {"com.lhstack.myblog.commons.ex.controller","com.lhstack.myblog.oauth","com.lhstack.myblog.api"})
public class MicroserviceMyblogOauthApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroserviceMyblogOauthApplication.class, args);
    }

}
