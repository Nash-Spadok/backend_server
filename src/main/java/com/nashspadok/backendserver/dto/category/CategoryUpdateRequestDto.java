package com.nashspadok.backendserver.dto.category;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class CategoryUpdateRequestDto {
    private String name;
    private MultipartFile image;
    private String description;
}
