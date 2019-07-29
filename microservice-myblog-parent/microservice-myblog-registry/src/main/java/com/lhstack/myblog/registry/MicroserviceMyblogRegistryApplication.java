package com.lhstack.myblog.registry;

import com.lhstack.myblog.limit.annotation.EnableRateLimit;
import com.lhstack.myblog.validcode.annotation.EnableValidCode;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EntityScan("com.lhstack.myblog.model")
@EnableTransactionManagement
@EnableValidCode
@ComponentScan({"com.lhstack.myblog.registry","com.lhstack.myblog.api","com.lhstack.myblog.commons"})
@EnableRateLimit
public class MicroserviceMyblogRegistryApplication {
    public static void main(String[] args) {
        SpringApplication.run(MicroserviceMyblogRegistryApplication.class, args);
    }

}
