package com.lhstack.myblog.limit;

import com.lhstack.myblog.limit.annotation.EnableRateLimit;
import com.lhstack.myblog.limit.service.TestService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

@SpringBootTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestService.class)
@EnableRateLimit
public class MicroserviceMyblogApplicationTest {
    @Autowired
    private TestService testService;
    @Test
    public void testHelloLimit() throws InterruptedException {
        for(int i = 0;i < 100;i++){
            Thread.sleep(100);
            List<String> strings = testService.helloList();
            System.out.println(strings);
        }
    }
    @Test
    public void testMapimit() throws InterruptedException {
        for(int i = 0;i < 100;i++){
            Thread.sleep(100);
            Map<String, String> stringStringMap = testService.helloMap();
            System.out.println(stringStringMap);
        }
    }
}