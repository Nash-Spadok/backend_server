package com.nashspadok.backendserver.model.product;

import com.nashspadok.backendserver.model.category.SubCategory;
import com.nashspadok.backendserver.model.file.ProductFile;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

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
    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy = "product",
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE}
    )
    private List<ProductFile> images = new ArrayList<>();

    @Column(nullable = false)
    private boolean isAvailable;

    @PrePersist
    public void prePersist() {
        isAvailable = true;
    }
}
