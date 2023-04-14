package br.com.component.extension.annotation;


import br.com.component.extension.enums.AutoGenerateStrategy;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface DynamoDbAutoGeneratePartitionKey {

    AutoGenerateStrategy strategy() default AutoGenerateStrategy.PartitionKey;
}
