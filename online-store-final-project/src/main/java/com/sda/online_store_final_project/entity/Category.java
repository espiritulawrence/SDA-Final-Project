package com.sda.online_store_final_project.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue
    @Column(name = "CATEGORY_ID")
    private Integer categoryId;

    @Column(name = "CATEGORY_NAME")
    private String categoryName;

    @NaturalId
    @Column(name = "CATEGORY_TYPE")
    private Integer categoryType;

    public Category(String categoryName, Integer categoryType) {
        this.categoryName = categoryName;
        this.categoryType = categoryType;
    }
}
