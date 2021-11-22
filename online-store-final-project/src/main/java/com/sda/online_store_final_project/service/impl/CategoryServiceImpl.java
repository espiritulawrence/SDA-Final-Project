package com.sda.online_store_final_project.service.impl;

import com.sda.online_store_final_project.entity.Category;
import com.sda.online_store_final_project.repository.CategoryRepository;
import com.sda.online_store_final_project.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryRepository categoryRepository;



    @Override
    public List<Category> findAll() {
        List<Category> res = categoryRepository.findAllByOrderByCategoryType();
        return res;
    }

    @Override
    public Category findByCategoryType(Integer categoryType) {
        Category res = categoryRepository.findByCategoryType(categoryType);
        return res;
    }

    @Override
    public List<Category> findByCategoryTypeIn(List<Integer> categoryTypeList) {
        List<Category> res = categoryRepository.findByCategoryTypeInOrderByCategoryTypeAsc(categoryTypeList);
        return res;
    }

    @Override
    @Transactional
    public Category save(Category category) {
        return categoryRepository.save(category);
    }



}
