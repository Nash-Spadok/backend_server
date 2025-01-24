package com.nash_spadok.backend_server.model.file;

import com.nash_spadok.backend_server.model.category.Category;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "category_files")
public class CategoryFile extends File {
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;
}
