package com.ds.starter.aegis;

import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.Resource;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.redisson.api.RRateLimiter;
import org.redisson.api.RedissonClient;
import org.springframework.core.annotation.Order;
import org.springframework.util.StringUtils;

/**
 * @author ds
 * @date 2025/2/26
 * @description
 */
@Order(30)
@Aspect
public class AegisAspect {

    @Resource
    private RedissonClient redissonClient;

    private final ConcurrentHashMap<String, RRateLimiter> rateLimiters = new ConcurrentHashMap<>();

    @Before("@annotation(rateLimiterAnno)")
    public void checkParameter(JoinPoint joinPoint, RateLimiter rateLimiterAnno) {
        String className = joinPoint.getSignature().getDeclaringType().getName();
        String methodName = joinPoint.getSignature().getName();
        String key = "aegis:" + className + "#" + methodName;

        if (!StringUtils.isEmpty(rateLimiterAnno.key())) {
            key = rateLimiterAnno.key();
        }

        RRateLimiter rateLimiter = rateLimiters.get(key);
        if (rateLimiter == null) {
            rateLimiter = redissonClient.getRateLimiter(key);
            rateLimiter.setRate(rateLimiterAnno.rateType(), rateLimiterAnno.rate(), rateLimiterAnno.rateInterval(), rateLimiterAnno.rateIntervalUnit());
            rateLimiters.put(key, rateLimiter);
        }

        boolean acquire = rateLimiter.tryAcquire(rateLimiterAnno.timeout(), rateLimiterAnno.unit());
        if (!acquire) {
            throw new RuntimeException("服务器繁忙,请稍后再试!");
        }
    }

}