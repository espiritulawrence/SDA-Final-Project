package com.sda.online_store_final_project.service;

import com.sda.online_store_final_project.entity.SubCategory;

import java.util.List;


public interface SubCategoryService {

    List<SubCategory> findAll();

    SubCategory findBySubCategoryId(Integer subCategoryId);

    SubCategory findBySubCategoryName(String subCategoryName);

    SubCategory save(SubCategory subCategory);

    void  delete(Integer subCategoryId);


}
