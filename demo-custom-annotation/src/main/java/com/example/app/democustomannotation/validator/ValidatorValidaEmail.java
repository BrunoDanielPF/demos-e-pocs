package com.example.app.democustomannotation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

public class ValidatorValidaEmail implements ConstraintValidator<ValidaEmail, CharSequence> {

    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    @Override
    public void initialize(ValidaEmail email) {
        // TODO
    }

    @Override
    public boolean isValid(CharSequence value, ConstraintValidatorContext context) {
        return value != null && Pattern.matches(EMAIL_PATTERN, value);
    }
}
