package com.ds.starter.aegis;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


/**
 * @author ds aegis
 */
@ConditionalOnProperty(name = "aegis.enabled", havingValue = "true")
@Configuration(proxyBeanMethods = false)
@ComponentScan(basePackages = {"com.ds.starter.aegis"})
public class AegisAutoConfiguration {

    @Bean
    public AegisAspect aegisAspect() {
        return new AegisAspect();
    }

}
