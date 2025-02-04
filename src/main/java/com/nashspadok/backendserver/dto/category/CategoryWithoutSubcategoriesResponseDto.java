package com.nashspadok.backendserver.dto.category;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryWithoutSubcategoriesResponseDto {
    private Long id;
    private String name;
    private Long imageUrlId;
    private String description;
}
