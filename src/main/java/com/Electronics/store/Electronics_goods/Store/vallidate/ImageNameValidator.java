package com.lcwd.electronic.store.validate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ImageNameValidator implements ConstraintValidator<com.lcwd.electronic.store.validate.ImageNameValid, String> {

    private Logger logger = LoggerFactory.getLogger(ImageNameValidator.class);

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        logger.info("Message from isValid : {} ", value);

        //logic
        if (value.isBlank()) {
            return false;
        } else {
            return true;
        }


    }
}
