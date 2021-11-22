package com.sda.online_store_final_project.service;

import com.sda.online_store_final_project.entity.Category;

import java.util.List;


public interface CategoryService {

    List<Category> findAll();

    Category findByCategoryType(Integer categoryType);

    List<Category> findByCategoryTypeIn(List<Integer> categoryTypeList);

    Category save(Category category);


}
