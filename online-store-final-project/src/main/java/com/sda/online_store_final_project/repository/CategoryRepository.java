package com.sda.online_store_final_project.repository;

import com.sda.online_store_final_project.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface CategoryRepository extends JpaRepository<Category, Integer> {
    // Some category
//    List<Category> findByCategoryIdInOrderByCategoryIdCategoryIdAsc(List<Integer> categoryId);
    // All category
    List<Category> findAllByOrderByCategoryId();
    // One category
//    Category findByCategoryType(Integer categoryType);

    Category findByCategoryId(Integer categoryId);


    Category findByCategoryName(String categoryName);
}
