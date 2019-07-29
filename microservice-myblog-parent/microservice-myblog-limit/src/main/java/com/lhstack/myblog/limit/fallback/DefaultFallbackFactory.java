package com.lhstack.myblog.limit.fallback;

import com.lhstack.myblog.limit.exception.LimitException;

public class DefaultFallbackFactory {
    public static Object fallback(Object key){
        throw new LimitException("限流回调方法:key = " + key.toString());
    }
}
