package com.nash_spadok.backend_server.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;

public class ImageFileListValidator implements ConstraintValidator<ValidImageFile, MultipartFile> {
    @Override
    public void initialize(ValidImageFile constraintAnnotation) {
    }

    @Override
    public boolean isValid(MultipartFile file, ConstraintValidatorContext context) {
        if (file == null || file.isEmpty()) {
            return true;
        }

        String contentType = file.getContentType();
        if (contentType == null || !contentType.startsWith("image/")) {
            return false;
        }

        if (file.getSize() > 5 * 1024 * 1024) {
            return false;
        }

        return Objects.equals(contentType, "image/jpeg")
                || Objects.equals(contentType, "image/png")
                || Objects.equals(contentType, "image/jpg");
    }
}
