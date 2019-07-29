package com.lhstack.myblog.limit.config;


import com.lhstack.myblog.limit.annotation.ResourceLimit;
import com.lhstack.myblog.limit.service.RateLimitServiceImpl;
import com.lhstack.myblog.limit.service.ResourceLimitService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;

import java.util.concurrent.locks.ReentrantReadWriteLock;

@Aspect
public class ResourceLimitAutoConfiguration {
    @Autowired
    private ResourceLimitService resourceLimitService;
    private static ReentrantReadWriteLock reentrantLock = new ReentrantReadWriteLock();

    //默认限流规则
    @Bean
    @ConditionalOnMissingBean
    public ResourceLimitService resourceLimitService(){
        return new RateLimitServiceImpl();
    }
    @Around("execution(public * *(..)) && @annotation(resourceLimit)")
    public Object around(ProceedingJoinPoint proceedingJoinPoint, ResourceLimit resourceLimit) throws Throwable {
        if(resourceLimitService.isExist(resourceLimit)){
            boolean b = resourceLimitService.proceedingRateLimit(resourceLimit);
            try{
                reentrantLock.readLock().lock();
                if(b){
                    return proceedingJoinPoint.proceed();
                }else{
                    return resourceLimitService.rateLimitFallback(resourceLimit,proceedingJoinPoint.getArgs());
                }
            }finally {
                reentrantLock.readLock().unlock();
            }
        }
        return proceedingJoinPoint.proceed();
    }

}
