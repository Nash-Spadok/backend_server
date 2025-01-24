package com.nash_spadok.backend_server.dto.product;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BookProductRespondDto {
    private Long id;
    private String title;
    private Long price;
    private Long subCategoryId;
    private String description;
    private List<Long> images;
    private String genre;
    private boolean isAvailable;
}
