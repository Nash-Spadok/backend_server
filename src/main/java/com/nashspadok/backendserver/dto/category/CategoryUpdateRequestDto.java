package com.nashspadok.backendserver.dto.category;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryUpdateRequestDto {
    private String name;
    private String key;
}
