package br.com.example.metric.demo.prop;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertiesPropertySource;

import java.util.Properties;

@Order(Ordered.HIGHEST_PRECEDENCE)
public class CustomPropertiesApplicationContextInitializerImpl implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        ConfigurableEnvironment environment = applicationContext.getEnvironment();
        Properties properties = new Properties();
        properties.put("propertie.application.name.identifier", "custom value to insert");
        environment.getPropertySources().addFirst(new PropertiesPropertySource("Custom-properties-source-application-initializer", properties));
    }
}
