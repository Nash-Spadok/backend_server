package com.nash_spadok.backend_server.dto.product;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
public class VyshyvankaProductRespondDto {
    private Long id;
    private String title;
    private Long price;
    private Long subCategoryId;
    private String description;
    private List<Long> images;
    private String size;
    private List<String> sizeAvailable;
    private boolean isAvailable;
}
