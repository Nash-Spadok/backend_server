package com.nashspadok.backendserver.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubCategoryResponseDto {
    private Long id;
    private String name;
    private String key;
    private Long categoryId;
    private String imageUrl;
}
