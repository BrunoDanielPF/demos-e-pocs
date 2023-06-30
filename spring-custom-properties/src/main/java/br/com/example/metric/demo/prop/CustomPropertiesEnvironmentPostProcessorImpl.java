package br.com.example.metric.demo.prop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertiesPropertySource;

import java.util.Properties;

@Order(Ordered.HIGHEST_PRECEDENCE)
public class CustomPropertiesEnvironmentPostProcessorImpl implements EnvironmentPostProcessor {

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        String name = application.getMainApplicationClass().getName();
        Properties properties = new Properties();
        properties.put("propertie-application-name-identifie", name);
        environment.getPropertySources().addFirst(new PropertiesPropertySource("Custom-properties-source-environment-post-processor", properties));
    }
}
