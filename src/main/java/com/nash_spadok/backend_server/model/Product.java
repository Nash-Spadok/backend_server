package com.nash_spadok.backend_server.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "products")
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String title;

    @Column(nullable = false)
    private Long price;

    @Column(nullable = false)
    private String size;

    @Column(nullable = false)
    private String imageUrl;

    @Column(nullable = false)
    private String maintenance;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sub_category_id", nullable = false)
    private SubCategory subCategory;

    @Column(nullable = false)
    private String sizeChartUrl;

    @Column(nullable = false)
    private String description;
}
