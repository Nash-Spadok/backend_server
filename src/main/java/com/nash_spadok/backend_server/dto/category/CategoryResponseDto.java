package com.nash_spadok.backend_server.dto.category;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CategoryResponseDto {
    private Long id;
    private String name;
    private Long imageUrlId;
    private String description;
    private List<Long> subCategoryIds;
}
