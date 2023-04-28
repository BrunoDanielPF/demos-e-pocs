package com.example.sqs.demosqspoc.configuration;

import com.example.sqs.demosqspoc.interceptor.CorrelationFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfiguration {

    @Bean
    public FilterRegistrationBean<CorrelationFilter> loggingFilter(){
        FilterRegistrationBean<CorrelationFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new CorrelationFilter());
        return registrationBean;
    }
}
