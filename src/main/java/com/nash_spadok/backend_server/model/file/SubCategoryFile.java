package com.nash_spadok.backend_server.model.file;

import com.nash_spadok.backend_server.model.category.SubCategory;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "sub_category_files")
public class SubCategoryFile extends File {
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sub_category_id", nullable = false)
    private SubCategory subCategory;
}
