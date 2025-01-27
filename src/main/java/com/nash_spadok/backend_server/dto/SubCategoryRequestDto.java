package com.nash_spadok.backend_server.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class SubCategoryRequestDto {
    @NotBlank(message = "Name is required")
    private String name;

    @NotNull(message = "Category is required")
    private Long categoryId;

    @NotBlank(message = "Image is required")
    private MultipartFile image;
}
