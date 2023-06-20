package com.ds.starter.swagger;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author ds swagger自动配置类
 */
@EnableSwagger2
@Configuration(proxyBeanMethods = false)
@ConditionalOnClass(SwaggerResource.class)
@EnableConfigurationProperties(SwaggerProperties.class)
@ComponentScan(basePackages = {"com.ds.starter.swagger"})
public class SwaggerAutoConfiguration {

    private final SwaggerProperties properties;

    public SwaggerAutoConfiguration(SwaggerProperties properties) {
        this.properties = properties;
    }

    @Bean
    @ConditionalOnMissingBean
    public Docket createRestApi() {
        // 响应状态
        List<ResponseMessage> responseMessages = new ArrayList<>();
        Map<Integer, String> responseMessagesConfig = properties.getResponseMessages();
        if (responseMessagesConfig != null) {
            responseMessagesConfig.forEach((code, message) -> {
                responseMessages.add(new ResponseMessageBuilder()
                        .code(code)
                        .message(message)
                        .responseModel(new ModelRef(message)).build());
            });
        }

        return new Docket(DocumentationType.SWAGGER_2)
                .globalResponseMessage(RequestMethod.GET, responseMessages)
                .enable(properties.getEnable())
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage(properties.getBasePackage()))
                .paths(PathSelectors.any()).build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(properties.getTitle())
                .description(properties.getDescription())
                .termsOfServiceUrl(properties.getHost())
                .contact(new Contact(properties.getAuthor(), properties.getUrl(), properties.getEmail()))
                .version(properties.getVersion()).build();
    }

}
