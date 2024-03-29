package com.example.app.democustomannotation.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Constraint(validatedBy = ValidatorValidaEmail.class)
@Target({FIELD, TYPE_USE, ANNOTATION_TYPE})
@Retention(RUNTIME)
public @interface ValidaEmail {

    String message() default "Endereço de e-mail inválido";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
