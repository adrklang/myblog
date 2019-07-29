package com.lhstack.myblog;

import com.lhstack.myblog.fallback.FallbackFactory;
import com.lhstack.myblog.limit.annotation.EnableRateLimit;
import com.lhstack.myblog.limit.annotation.ResourceLimit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableRateLimit
@RestController
public class MicroserviceMyblogLimitTestApplication {

    /**
     * 每5s填充一枚令牌，初始化令牌桶100
     * @return
     */
    @GetMapping("hello")
    @ResourceLimit(key = "hello",seconds = 5,count = 10)
    public String hello(){
        return "Hello World";
    }

    @GetMapping("hello/{message}")
    @ResourceLimit(key="message",seconds = 1,count = 5,fallbackFactory = FallbackFactory.class,method = "message")
    public String message(@PathVariable("message") String message){
        return "hello " + message;
    }

    public static void main(String[] args) {
        SpringApplication.run(MicroserviceMyblogLimitTestApplication.class, args);
    }

}
