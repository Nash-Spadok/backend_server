package com.nashspadok.backendserver.dto.product;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

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
