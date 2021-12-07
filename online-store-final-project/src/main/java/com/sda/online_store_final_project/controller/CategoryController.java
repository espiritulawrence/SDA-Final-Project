package com.sda.online_store_final_project.controller;

import com.sda.online_store_final_project.entity.Category;
import com.sda.online_store_final_project.entity.Product;
import com.sda.online_store_final_project.service.CategoryService;
import com.sda.online_store_final_project.service.ProductService;
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
public class CategoryController {
    @Autowired
    CategoryService categoryService;
    @Autowired
    ProductService productService;


    @GetMapping("/category/{id}")
    public ModelAndView showOne(@PathVariable("id") Integer categoryId,
                                @RequestParam(value = "page", defaultValue = "1") Integer page,
                                @RequestParam(value = "size", defaultValue = "3") Integer size,
                                Map<String, Object> map, Model model) {

        Category res = categoryService.findByCategoryId(categoryId);
        PageRequest request = PageRequest.of(page - 1, size);
        Page<Product> productInCategory = productService.findAllInCategory(categoryId, request);
        map.put("category", res);
        map.put("products", productInCategory);
        map.put("currentPage", page);
        map.put("size", size);
        return new ModelAndView("show-category", map);
    }
    @GetMapping("/admin/categories")
    public String listCategory(Model model) {
        List<Category> listCategories = categoryService.findAll();
        model.addAttribute("listCategories", listCategories);

        return "admin-categories";
    }

    @GetMapping("/admin/categories/add")
    public String addCategory(Category category, Model model){
        model.addAttribute("category", category);
        return "categoriesAdd";
    }

    @GetMapping("/admin/categories/edit/{id}")
    public String editCategory(@PathVariable("id") Integer id, Model model){
        Category category = categoryService.findByCategoryId(id);

        model.addAttribute("category", category);

        return "categoriesAdd";
    }

    @PostMapping("/admin/categories/save")
    public String saveCategory(@Valid @ModelAttribute("category") Category category, BindingResult result, Model model){
        model.addAttribute("category", category);

        if(result.hasErrors()){
            return "admin-categories";
        }
        categoryService.save(category);

        return "redirect:/admin/categories"+"?success";
    }
    @GetMapping("/admin/categories/delete/{id}")
   public String deleteCategory(@PathVariable("id") Integer id, Model model) {
       categoryService.delete(id);

        return "redirect:/admin/admin-categories";
   }


}
