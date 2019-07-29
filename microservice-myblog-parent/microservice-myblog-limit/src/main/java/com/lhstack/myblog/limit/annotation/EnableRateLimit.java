package com.lhstack.myblog.limit.annotation;

import com.lhstack.myblog.limit.config.ResourceLimitAutoConfiguration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@EnableAspectJAutoProxy
@Import({ResourceLimitAutoConfiguration.class})
public @interface EnableRateLimit {
}
