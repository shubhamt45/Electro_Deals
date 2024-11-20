package com.lcwd.electronic.store.validate;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = com.lcwd.electronic.store.validate.ImageNameValidator.class)
public @interface ImageNameValid {
//com.lcwd.electronic.store.validate.ImageNameValidator.class
    //error message
    String message() default "Invalid Image Name !!";

    //represent group of constraints
    Class<?>[] groups() default {};

    //additional information about annotation
    Class<? extends Payload>[] payload() default {};


}
