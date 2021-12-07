package com.sda.online_store_final_project.service.impl;

import com.sda.online_store_final_project.entity.Category;
import com.sda.online_store_final_project.enums.ResultEnum;
import com.sda.online_store_final_project.exception.MyException;
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
        List<Category> res = categoryRepository.findAllByOrderByCategoryId();
        return res;
    }

    @Override
    public Category findByCategoryId(Integer categoryId) {
        Category res = categoryRepository.findByCategoryId(categoryId);
        if(res == null) throw new MyException(ResultEnum.CATEGORY_NOT_FOUND);
        return res;
    }


    @Override
    public Category findByCategoryName(String categoryName) {
        Category res = categoryRepository.findByCategoryName(categoryName);
        if(res == null) throw new MyException(ResultEnum.CATEGORY_NOT_FOUND);
        return res;
    }

    @Override
    @Transactional
    public Category save(Category category) {
        return categoryRepository.save(category);
    }
    @Override
    public void delete (Integer categoryId) {
        categoryRepository.deleteById(categoryId);
    }
}
