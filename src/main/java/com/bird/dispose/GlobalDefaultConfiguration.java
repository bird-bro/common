package com.bird.dispose;

import com.bird.dispose.exception.GlobalDefaultExceptionHandler;
import com.bird.dispose.advice.CommonResponseDataAdvice;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * bean配置加载
 * {@link GlobalDefaultExceptionHandler} {@link CommonResponseDataAdvice}
 * @Description TODO
 * @Date 16:17 2020/6/24
 * @Created wangpeng
 */
@Configuration
@EnableConfigurationProperties(GlobalDefaultProperties.class)
@PropertySource(value = "classpath:dispose.properties", encoding = "UTF-8")
public class GlobalDefaultConfiguration {

    @Bean
    public GlobalDefaultExceptionHandler globalDefaultExceptionHandler() {
        return new GlobalDefaultExceptionHandler();
    }

    @Bean
    public CommonResponseDataAdvice commonResponseDataAdvice(GlobalDefaultProperties globalDefaultProperties) {
        return new CommonResponseDataAdvice(globalDefaultProperties);
    }

}
