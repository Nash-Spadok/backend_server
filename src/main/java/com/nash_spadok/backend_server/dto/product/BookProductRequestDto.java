package com.nash_spadok.backend_server.dto.product;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
public class BookProductRequestDto {
    @NotBlank(message = "Title is mandatory")
    private String title;

    @Min(value = 0, message = "Price must be greater than 0")
    @NonNull
    private Long price;

    private Long subCategoryId;

    @NotBlank(message = "Color is mandatory")
    private String description;

    private String genre;

    private String size;

    private List<MultipartFile> images;

    private boolean isABook;
}
