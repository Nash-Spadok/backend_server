package com.nash_spadok.backend_server.model.category;

import com.nash_spadok.backend_server.model.file.CategoryFile;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @OneToOne(
            mappedBy = "category",
            fetch = FetchType.LAZY,
            cascade = CascadeType.PERSIST)
    private CategoryFile categoryFile;

    @Column(nullable = false)
    private String description;

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    private List<SubCategory> subCategories = new ArrayList<>();
}
