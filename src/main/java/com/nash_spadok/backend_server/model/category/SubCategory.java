package com.nash_spadok.backend_server.model.category;

import com.nash_spadok.backend_server.model.file.SubCategoryFile;
import com.nash_spadok.backend_server.model.product.Product;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@Table(name = "sub_categories")
public class SubCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @OneToOne(mappedBy = "subCategory", fetch = FetchType.LAZY)
    private SubCategoryFile subCategoryFile;

    @OneToMany(mappedBy = "subCategory", fetch = FetchType.LAZY)
    private List<Product> products = new ArrayList<>();
}
