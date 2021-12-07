package com.sda.online_store_final_project.service;

import com.sda.online_store_final_project.entity.Category;

import java.util.List;


public interface CategoryService {

    List<Category> findAll();

    Category findByCategoryId(Integer categoryId);

    Category findByCategoryName(String categoryName);

    Category save(Category category);

    void  delete(Integer categoryId);


}
