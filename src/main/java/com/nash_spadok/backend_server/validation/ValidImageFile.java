package com.nash_spadok.backend_server.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ImageFileListValidator.class)
@Target({ElementType.TYPE_USE, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidImageFile {
    String message() default "Invalid image file, "
            + "image size must be maximum 5mb and 365x420, "
            + "file type png or jpg or jpeg";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
