package com.lhstack.myblog.limit.config;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.concurrent.TimeUnit;

@Data
@Accessors(chain = true)
public class RateLimiter {
    private Long time;
    private com.google.common.util.concurrent.RateLimiter rateLimiter;

    public RateLimiter(long currentTimeMillis, com.google.common.util.concurrent.RateLimiter rateLimiter) {
        this.time = currentTimeMillis;
        this.rateLimiter = rateLimiter;
    }

    public static RateLimiter create(long count, long seconds, TimeUnit seconds1) {
        return new RateLimiter(System.currentTimeMillis(),com.google.common.util.concurrent.RateLimiter.create(count,seconds,seconds1));
    }

    public Boolean tryAcquire(){
        return rateLimiter.tryAcquire();
    }
}
