package com.sda.online_store_final_project.controller;


import com.sda.online_store_final_project.entity.Category;
import com.sda.online_store_final_project.entity.Product;
import com.sda.online_store_final_project.entity.SubCategory;
import com.sda.online_store_final_project.enums.ProductStatusEnum;
import com.sda.online_store_final_project.service.CategoryService;
import com.sda.online_store_final_project.service.ProductService;
import com.sda.online_store_final_project.service.SubCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;


@Controller
public class ProductController {
    @Autowired
    CategoryService categoryService;
    @Autowired
    SubCategoryService subCategoryService;
    @Autowired
    ProductService productService;

    @GetMapping("/product")
    public String findAll(Model model) {
        List<Category> categories = categoryService.findAll();
        model.addAttribute("categories", categories);

        List<SubCategory> subCategories = subCategoryService.findAll();
        model.addAttribute("subCategories", subCategories);

        List<Product> listProducts = productService.findAll();
        model.addAttribute("listProducts", listProducts);

        return "list-product";
    }

    @GetMapping("/admin/product")
    public String adminFindAll(Model model) {
        List<Category> categories = categoryService.findAll();
        model.addAttribute("categories", categories);

        List<SubCategory> subCategories = subCategoryService.findAll();
        model.addAttribute("subCategories", subCategories);

        List<Product> listProducts = productService.findAll();
        model.addAttribute("listProducts", listProducts);

        return "admin-list-product";
    }


    @GetMapping("/product/{productId}")
    public String showOne(@PathVariable("productId") String productId, Model model) {

        Product product = productService.findOne(productId);

        // Product is not available
        if (product.getProductStatus() == ProductStatusEnum.DOWN.getCode()) {
            model.addAttribute("msg", "Product is unavailable!");
            model.addAttribute("url", "/");
            return  "common/error";
        }
        model.addAttribute(product);
        return "/show";
    }



    @PostMapping("/admin/product/new")
    public String create(
            @RequestParam("categoryId") Integer categoryId,
            @RequestParam("subCategoryId") Integer subCategoryId,
            @RequestParam("productStatus") Integer productStatus,
            @Valid @ModelAttribute("product") Product product,
            RedirectAttributes redirectAttributes,
            BindingResult bindingResult, Model model) {
        model.addAttribute("product", product);
        Product productIdExists = productService.findOne(product.getProductId());
        if (productIdExists != null) {
            bindingResult
                    .rejectValue("productId", "error.product",
                            "There is already a product with the code provided");
        }
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("product", product);
            return "/add-edit-product";
        }
        product.setProductStatus(productStatus);
        product.setCategoryId(categoryId);
        product.setSubCategoryId(subCategoryId);
        productService.save(product);
//        return "redirect:" + "/";
        return "/add-edit-product";

    }

    @GetMapping("/admin/product/new")
    public String createForm(Product product,
                             Model model) {

        model.addAttribute("product", product);
        return "/add-edit-product";
    }

    @GetMapping("/admin/product/edit/{id}")
    public String productEdit(@PathVariable("id") String productId,
                              Model model){
        Product product = productService.findOne(productId);
        model.addAttribute("product", product);

        return "/add-edit-product";
    }

    @PostMapping("/admin/product/edit/{id}/")
    public String edit(@PathVariable("id") String productId,
                       @RequestParam("categoryId") Integer categoryId,
                       @RequestParam("subCategoryId") Integer subCategoryId,
                       @RequestParam("productStatus") Integer productStatus,
                       @Valid @ModelAttribute("product") Product product,
                       RedirectAttributes redirectAttributes,
                       BindingResult bindingResult, Model model){

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("product", product);
            return "/admin/product/edit/" + productId ;

        }
        if (!productId.equals(product.getProductId())) {
            model.addAttribute("msg", "Product id does not exist!");
            model.addAttribute("url", "/");
            return  "common/error";
        }
        product.setCategoryId(categoryId);
        product.setSubCategoryId(subCategoryId);
        product.setProductStatus(productStatus);
        productService.update(product);
        return "redirect:" + "/";
    }

    @PostMapping("/admin/product/save")
    public String saveProduct(@Valid @ModelAttribute("product") Product product, BindingResult result, Model model){
        model.addAttribute("product", product);
        List<SubCategory> subCategories = subCategoryService.findAll();
        model.addAttribute("subcategories",subCategories);

        if(result.hasErrors()){
            return "/add-edit-product";
        }

        productService.save(product);
        return "redirect:" + "/";
    }

    @GetMapping("/admin/product/delete/{id}")
    public String delete(@PathVariable("id") String productId, Model model) {
        productService.delete(productId);
        return "redirect:" + "/";
    }

    @GetMapping("/search")
    public String search(@Param("keyword") String keyword, Model model) {
        List<Product> searchResult = productService.search(keyword);
        model.addAttribute("keyword", keyword);
        model.addAttribute("pageTitle", "Search results for '" + keyword + "'");
        model.addAttribute("searchResult", searchResult);

        return "search-results";
    }

}
