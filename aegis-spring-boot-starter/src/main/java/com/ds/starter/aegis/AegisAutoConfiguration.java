package com.ds.starter.aegis;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


/**
 * @author ds aegis
 */
@Configuration(proxyBeanMethods = false)
@ComponentScan(basePackages = {"com.ds.starter.aegis"})
public class AegisAutoConfiguration {

    @Bean
    public AegisAspect aegisAspect() {
        return new AegisAspect();
    }

}
