package com.sda.online_store_final_project.service.impl;

import com.sda.online_store_final_project.entity.SubCategory;
import com.sda.online_store_final_project.enums.ResultEnum;
import com.sda.online_store_final_project.exception.MyException;
import com.sda.online_store_final_project.repository.SubCategoryRepository;
import com.sda.online_store_final_project.service.SubCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class SubCategoryServiceImpl implements SubCategoryService {

    @Autowired
    SubCategoryRepository subCategoryRepository;


    @Override
    public List<SubCategory> findAll() {
        List<SubCategory> res = subCategoryRepository.findAllByOrderBySubCategoryId();
        return res;
    }

    @Override
    public SubCategory findBySubCategoryId(Integer subCategoryId) {
        SubCategory res = subCategoryRepository.findBySubCategoryId(subCategoryId);
        if(res == null) throw new MyException(ResultEnum.SUBCATEGORY_NOT_FOUND);
        return res;
    }

    @Override
    public SubCategory findBySubCategoryName(String subCategoryName) {
        SubCategory res = subCategoryRepository.findBySubCategoryName(subCategoryName);
        if(res == null) throw new MyException(ResultEnum.SUBCATEGORY_NOT_FOUND);
        return res;
    }

    @Override
    @Transactional
    public SubCategory save(SubCategory subCategory) {

        return subCategoryRepository.save(subCategory);
    }

    @Override
    public void delete (Integer subCategoryId) {

        subCategoryRepository.deleteById(subCategoryId);
    }
}
