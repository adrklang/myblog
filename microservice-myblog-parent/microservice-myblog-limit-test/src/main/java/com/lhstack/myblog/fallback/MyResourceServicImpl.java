package com.lhstack.myblog.fallback;

import com.lhstack.myblog.limit.annotation.ResourceLimit;
import com.lhstack.myblog.limit.service.ResourceLimitService;
import org.springframework.stereotype.Component;

@Component("MyResourceServicImpl")
public class MyResourceServicImpl implements ResourceLimitService {
    @Override
    public Boolean isExist(ResourceLimit resourceLimit) {
        System.out.println("自定义限流");
        return true;
    }

    @Override
    public boolean proceedingRateLimit(ResourceLimit resourceLimit) {
        return false;
    }

    @Override
    public Object rateLimitFallback(ResourceLimit resourceLimit, Object... args) throws Exception {
        return null;
    }
}
