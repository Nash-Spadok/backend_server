package com.nash_spadok.backend_server.dto.product;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class ProductRequestDto {
    @NotBlank(message = "Title is mandatory")
    private String title;

    @Min(value = 0, message = "Price must be greater than 0")
    @NonNull
    private Long price;

    @NotBlank(message = "Size is mandatory")
    private String size;

    @NotBlank(message = "Color is mandatory")
    private String description;

    @NotBlank(message = "Image URL is mandatory")
    private String imageUrl;

    @NotBlank(message = "Maintenance is mandatory")
    private String maintenance;

    @NonNull
    private Long subCategoryId;

    @NotBlank(message = "Size chart URL is mandatory")
    private String sizeChartUrl;
}
