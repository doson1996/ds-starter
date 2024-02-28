package com.ds.starter.check.annotion;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * todo 子类拿不到注解
 * 加上此注解的方法执行参数校验 {@see RequestCheck.check}
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface CheckParam {

}
