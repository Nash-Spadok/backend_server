package com.nashspadok.backendserver.dto.category;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryResponseDto {
    private Long id;
    private String name;
    private Long imageUrlId;
    private String description;
    private List<Long> subCategoryIds;
}
