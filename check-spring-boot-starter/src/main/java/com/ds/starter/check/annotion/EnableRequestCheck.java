package com.ds.starter.check.annotion;

import com.ds.starter.check.EnableRequestCheckSelector;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author ds
 * @date 2024/2/20
 * @description 开启参数检查
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(EnableRequestCheckSelector.class)
public @interface EnableRequestCheck {

}
