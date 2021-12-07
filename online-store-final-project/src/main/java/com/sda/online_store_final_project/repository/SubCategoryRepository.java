package com.sda.online_store_final_project.repository;

import com.sda.online_store_final_project.entity.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface SubCategoryRepository extends JpaRepository<SubCategory, Integer> {
//    List<SubCategory> findBySubCategoryTypeInOrderBySubCategoryTypeAsc(List<Integer> subCategoryTypes);
    List<SubCategory> findAllByOrderBySubCategoryId();
    SubCategory findBySubCategoryId(Integer subCategoryId);
    SubCategory findBySubCategoryName(String subCategoryName);
}
