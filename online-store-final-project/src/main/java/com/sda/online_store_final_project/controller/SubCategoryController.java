package com.sda.online_store_final_project.controller;

import com.sda.online_store_final_project.entity.Product;
import com.sda.online_store_final_project.entity.SubCategory;
import com.sda.online_store_final_project.service.ProductService;
import com.sda.online_store_final_project.service.SubCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;


@Controller
public class SubCategoryController {
    @Autowired
    SubCategoryService subCategoryService;
    @Autowired
    ProductService productService;


    @GetMapping("/subcategory/{id}")
    public ModelAndView showOne(@PathVariable("id") Integer subCategoryId,
                                @RequestParam(value = "page", defaultValue = "1") Integer page,
                                @RequestParam(value = "size", defaultValue = "3") Integer size,
                                Map<String, Object> map) {

        SubCategory res = subCategoryService.findBySubCategoryId(subCategoryId);
        PageRequest request = PageRequest.of(page - 1, size);
        Page<Product> productInSubCategory = productService.findAllInSubCategory(subCategoryId, request);
        map.put("subcategory", res);
        map.put("products", productInSubCategory);
        map.put("currentPage", page);
        map.put("size", size);
        return new ModelAndView("show-subcategory", map);
    }
    @GetMapping("/subcategory/list")
    public String listSubCategory(Model model) {
        List<SubCategory> listSubCategories = subCategoryService.findAll();
        model.addAttribute("listSubCategories", listSubCategories);

        return "/subcategory/list-subcategory";
    }

    @GetMapping("/subcategory/add")
    public String addSubCategory(SubCategory subCategory, Model model){
        model.addAttribute("subcategory", subCategory);
        return "/category/add-edit-subcategory";
    }

    @GetMapping("/subcategory/edit/{id}")
    public String editSubCategory(@PathVariable("id") Integer id, Model model){
        SubCategory subCategory = subCategoryService.findBySubCategoryId(id);

        model.addAttribute("subcategory", subCategory);

        return "/subcategory/add-edit-subcategory";
    }

    @PostMapping("/subcategory/save")
    public String saveSubCategory(@Valid @ModelAttribute("subcategory") SubCategory subCategory, BindingResult result, Model model){
        model.addAttribute("subcategory", subCategory);

        if(result.hasErrors()){
            return "/subcategory/add-edit-subcategory";
        }
        subCategoryService.save(subCategory);

        return "redirect:/subcategory/list"+"?success";
    }
    @GetMapping("/subcategory/delete/{id}")
    public String deleteSubCategory(@PathVariable("id") Integer id, Model model) {
        subCategoryService.delete(id);

        return "redirect:/subcategory/list"+"?deleted";
    }
}
