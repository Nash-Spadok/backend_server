package com.nash_spadok.backend_server.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductRespondDto {
    private Long id;
    private String title;
    private Long price;
    private String size;
    private String description;
    private String imageUrl;
    private String maintenance;
    private Long categoryId;
    private String sizeChartUrl;
}
