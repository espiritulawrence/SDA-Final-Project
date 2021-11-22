package com.sda.online_store_final_project.repository;

import com.sda.online_store_final_project.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface CategoryRepository extends JpaRepository<Category, Integer> {
    // Some category
    List<Category> findByCategoryTypeInOrderByCategoryTypeAsc(List<Integer> categoryTypes);
    // All category
    List<Category> findAllByOrderByCategoryType();
    // One category
    Category findByCategoryType(Integer categoryType);
}
