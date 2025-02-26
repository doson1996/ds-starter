package com.ds.starter.aegis;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.concurrent.TimeUnit;

import org.redisson.api.RateIntervalUnit;
import org.redisson.api.RateType;

/**
 * @author ds
 * @date 2025/2/26
 * @description
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RateLimiter {

    /**
     * 指定限流key，防止类+方法名重复
     *
     * @return
     */
    String key() default "";

    /**
     * 限流速率
     *
     * @return
     */
    long rate() default 10L;

    /**
     * 限流速率间隔
     *
     * @return
     */
    long rateInterval() default 100L;

    /**
     * 限流速率间隔时间单位
     *
     * @return
     */
    RateIntervalUnit rateIntervalUnit() default RateIntervalUnit.MILLISECONDS;

    /**
     * 限流制类型
     *
     * @return
     */
    RateType rateType() default RateType.OVERALL;

    /**
     * 限流等待时间
     *
     * @return
     */
    long timeout() default 200L;

    /**
     * 限流等待时间单位
     *
     * @return
     */
    TimeUnit unit() default TimeUnit.MILLISECONDS;

}