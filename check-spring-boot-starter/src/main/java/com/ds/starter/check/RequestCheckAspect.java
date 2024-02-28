package com.ds.starter.check;

import com.ds.nas.lib.common.base.request.BaseRequest;
import com.ds.starter.check.annotion.CheckParam;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;

/**
 * @author ds
 * @date 2023/1/26
 * @description service层请求参数校验切面
 */
@Slf4j
@Order(10)
@Aspect
public class RequestCheckAspect {

    /**
     * 参数校验
     *
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Before("@annotation(checkParam)")
    public void checkParameter(JoinPoint joinPoint, CheckParam checkParam) throws Throwable {
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            if (arg instanceof BaseRequest) {
                RequestCheck.check((BaseRequest) arg);
            }
        }
    }

}
