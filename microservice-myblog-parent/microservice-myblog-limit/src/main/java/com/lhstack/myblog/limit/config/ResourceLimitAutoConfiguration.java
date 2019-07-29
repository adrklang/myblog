package com.lhstack.myblog.limit.config;


import com.lhstack.myblog.limit.config.RateLimiter;
import com.lhstack.myblog.limit.annotation.ResourceLimit;
import com.lhstack.myblog.limit.fallback.DefaultFallbackFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Aspect
public class ResourceLimitAutoConfiguration {

    @Autowired
    private HttpSession session;
    private static Logger logger = LoggerFactory.getLogger(ResourceLimitAutoConfiguration.class);
    private static ReentrantReadWriteLock reentrantLock = new ReentrantReadWriteLock();
    private static ConcurrentHashMap<String,RateLimiter> concurrentHashMap = new ConcurrentHashMap<>();
    private static ReentrantLock writeLock = new ReentrantLock();

    @PostConstruct
    public void listener(){
        new Thread(() ->{
            while(true){
                try {
                    Thread.sleep(1000);//每过1s检查一次
                    Set<Map.Entry<String, RateLimiter>> entries = concurrentHashMap.entrySet();
                    for(Map.Entry<String, RateLimiter> s:entries){
                        String key = s.getKey();
                        RateLimiter value = s.getValue();
                        Long time = value.getTime();
                        Long currentTime = System.currentTimeMillis();
                        if(currentTime - time >= 1800000){//30分钟
                            try{
                                writeLock.lock();
                                concurrentHashMap.remove(key);
                                logger.info("session超时，已删除:" + key);
                            }finally {
                                writeLock.unlock();
                            }
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Around("execution(public * *(..)) && @annotation(resourceLimit)")
    public Object around(ProceedingJoinPoint proceedingJoinPoint, ResourceLimit resourceLimit) throws Throwable {
        if(isExist(resourceLimit)){
            boolean b = proceedingRateLimit(resourceLimit);
            try{
                reentrantLock.readLock().lock();
                if(b){
                    return proceedingJoinPoint.proceed();
                }else{
                    return rateLimitFallback(resourceLimit,proceedingJoinPoint.getArgs());
                }
            }finally {
                reentrantLock.readLock().unlock();
            }
        }
        return null;
    }

    private Object rateLimitFallback(ResourceLimit resourceLimit,Object ... args) throws Exception {
        Class<?> fallbackFactoryClass = resourceLimit.fallbackFactory();
        if(fallbackFactoryClass == DefaultFallbackFactory.class){
            Method fallback = fallbackFactoryClass.getMethod("fallback",Object.class);
            Object invoke = fallback.invoke(null,resourceLimit.key());
            return invoke;
        }
        Method[] methods = fallbackFactoryClass.getMethods();
        Method targetMethod = null;
        for(Method method : methods){
            if(method.getName().equals(resourceLimit.method())){
                targetMethod = method;
                break;
            }
        }
        Object invoke = targetMethod.invoke(null, args);
        return invoke;
    }

    private boolean proceedingRateLimit(ResourceLimit resourceLimit) {
        String key = session.getId() + resourceLimit.key();
        try{
            reentrantLock.writeLock().lock();
            RateLimiter rateLimiter = concurrentHashMap.get(key);
            if(rateLimiter.tryAcquire()){
                return true;
            }
            return false;
        }finally {
            reentrantLock.writeLock().unlock();
        }
    }

    private Boolean isExist(ResourceLimit resourceLimit){
        String key = session.getId() + resourceLimit.key();
        long count = resourceLimit.count();
        long seconds = resourceLimit.seconds();
        if(!concurrentHashMap.containsKey(key)){
            synchronized (ResourceLimitAutoConfiguration.class){
                if(!concurrentHashMap.containsKey(key)){
                    RateLimiter rateLimiter = RateLimiter.create(count, seconds, TimeUnit.SECONDS);
                    concurrentHashMap.put(key,rateLimiter);
                    logger.info("初始化RateLimiter -- {},key -- {}",rateLimiter,key);
                }
            }
        }
        return true;
    }
}
