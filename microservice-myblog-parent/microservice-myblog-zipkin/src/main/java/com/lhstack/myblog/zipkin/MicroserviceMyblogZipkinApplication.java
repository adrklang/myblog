package com.lhstack.myblog.zipkin;

import com.lhstack.myblog.validcode.annotation.EnableValidCode;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@ComponentScan(value = {"com.lhstack.myblog.api","com.lhstack.myblog.zipkin"})
//@EnableDiscoveryClient
@Configuration
@EnableValidCode
public class MicroserviceMyblogZipkinApplication{

    @Bean("restTemplate")
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    @Bean("loadBalance")
    public RestTemplate loadBalanceRestTemplate(){
        return new RestTemplate();
    }
    public static void main(String[] args) {
        SpringApplication.run(MicroserviceMyblogZipkinApplication.class, args);
    }
}
