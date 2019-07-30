package com.lhstack.myblog.fallback;

import com.lhstack.myblog.limit.annotation.ResourceLimit;
import com.lhstack.myblog.limit.model.LimitService;
import com.lhstack.myblog.limit.model.LimitType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
@Service
public class TestService {

    @ResourceLimit(key="message",seconds = 1, capacity = 5,fallbackFactory = FallbackFactory.class,method = "message",type = LimitType.IP,useLimitService = LimitService.JDK,secondsAddCount = 2)
    public String ipMessage(String message){
        return "hello " + message;
    }
}
