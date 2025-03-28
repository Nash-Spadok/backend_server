package com.nashspadok.backendserver.dto.category;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class CategoryRequestDto {
    @NotBlank(message = "Name is mandatory")
    private String name;

    @NotBlank(message = "Key is mandatory")
    private String key;

    @NotBlank(message = "Image is mandatory")
    private MultipartFile image;
}
