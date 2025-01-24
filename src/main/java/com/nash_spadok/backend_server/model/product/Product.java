package com.nash_spadok.backend_server.model.product;

import com.nash_spadok.backend_server.model.order.Order;
import com.nash_spadok.backend_server.model.category.SubCategory;
import com.nash_spadok.backend_server.model.file.ProductFile;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "products")
@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String title;

    @Column(nullable = false)
    private BigDecimal price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sub_category_id", nullable = false)
    private SubCategory subCategory;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
    private List<ProductFile> images = new ArrayList<>();

    @Column(nullable = false)
    private boolean isAvailable;

    @PrePersist
    public void prePersist() {
        isAvailable = true;
    }
}
