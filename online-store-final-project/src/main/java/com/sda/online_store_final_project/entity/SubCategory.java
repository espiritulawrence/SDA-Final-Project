package com.sda.online_store_final_project.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "subcategory")
public class SubCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SUBCATEGORY_ID")
    private Integer subCategoryId;

    @Column(name = "CATEGORY_ID")
    private Integer categoryId;

    @Column(name = "SUBCATEGORY_NAME")
    private String subCategoryName;

    public SubCategory(String subCategoryName, Integer subCategoryId, Integer categoryId) {
        this.subCategoryName = subCategoryName;
        this.subCategoryId = subCategoryId;
        this.categoryId = categoryId;
    }
}
