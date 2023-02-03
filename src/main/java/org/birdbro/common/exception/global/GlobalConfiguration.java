package org.birdbro.common.exception.global;

import org.birdbro.common.exception.advice.CommonResponseDataAdvice;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author bird
 * @date 2021-7-21 8:54
 **/
@Configuration
@EnableConfigurationProperties(GlobalProperties.class)
@PropertySource(value = "classpath:dispose.properties", encoding = "UTF-8")
public class GlobalConfiguration {
    @Bean
    public GlobalExceptionHandler globalDefaultExceptionHandler() {
        return new GlobalExceptionHandler();
    }

    @Bean
    public CommonResponseDataAdvice commonResponseDataAdvice(GlobalProperties globalDefaultProperties) {
        return new CommonResponseDataAdvice(globalDefaultProperties);
    }
}
