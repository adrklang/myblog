package com.lhstack.myblog.limit.annotation;

import com.lhstack.myblog.limit.model.LimitType;
import com.lhstack.myblog.limit.fallback.DefaultFallbackFactory;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ResourceLimit {
    long count() default 5;//seconds秒内请求次数
    String key();
    long seconds() default 1;//设定在指定秒内的请求次数
    Class<?> fallbackFactory() default DefaultFallbackFactory.class;
    String method() default "fallback";
    LimitType type() default LimitType.SESSION;
}
