package com.nash_spadok.backend_server.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class SubCategoryRequestDto {
    @NotBlank(message = "Name is required")
    private String name;

    @NonNull
    private Long categoryId;
}
