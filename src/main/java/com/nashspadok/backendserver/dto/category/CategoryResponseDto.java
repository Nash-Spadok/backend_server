package com.nashspadok.backendserver.dto.category;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryResponseDto {
    private Long id;
    private String name;
    private String key;
    private List<Long> subCategoryIds;
}
