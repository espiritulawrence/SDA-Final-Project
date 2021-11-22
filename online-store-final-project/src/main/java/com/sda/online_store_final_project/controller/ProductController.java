package com.sda.online_store_final_project.controller;

import com.sda.online_store_final_project.entity.Product;
import com.sda.online_store_final_project.service.CategoryService;
import com.sda.online_store_final_project.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class ProductController {
    @Autowired
    CategoryService categoryService;
    @Autowired
    ProductService productService;

    @GetMapping("/product")
    public Page<Product> getAllProducts(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                        @RequestParam(value = "size", defaultValue = "3") Integer size) {
        PageRequest request = PageRequest.of(page - 1, size);
        return productService.findAll(request);
    }

    @GetMapping("/product/{productId}")
    public Product getProductById(@PathVariable("productId") String productId) {

        Product product = productService.findOne(productId);


        return product;
    }

    @PostMapping("/seller/product/new")
    public ResponseEntity addProduct(@Valid @RequestBody Product product,
                                 BindingResult bindingResult) {
        Product productIdExists = productService.findOne(product.getProductId());
        if (productIdExists != null) {
            bindingResult.rejectValue("productId", "error.product",
                            "There is already a product with the code provided");
        }
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult);
        }
        return ResponseEntity.ok(productService.save(product));
    }

    @PutMapping("/seller/product/{id}/edit")
    public ResponseEntity updateProduct(@PathVariable("id") String productId,
                               @Valid @RequestBody Product product,
                               BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult);
        }
        if (!productId.equals(product.getProductId())) {
            return ResponseEntity.badRequest().body("Id Not Matched");
        }

        return ResponseEntity.ok(productService.update(product));
    }

    @DeleteMapping("/seller/product/{id}/delete")
    public ResponseEntity deleteProduct(@PathVariable("id") String productId) {
        productService.delete(productId);
        return ResponseEntity.ok().build();
    }
}