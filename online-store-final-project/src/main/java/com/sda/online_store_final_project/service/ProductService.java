package com.sda.online_store_final_project.service;

import com.sda.online_store_final_project.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface ProductService {

    Product findOne(String productId);

    // all selling products
    Page<Product> findUpAll(Pageable pageable);
    // all products
    Page<Product> findAll(Pageable pageable);
    // all products in a category
    Page<Product> findAllInCategory(Integer categoryId, Pageable pageable);

    // all products in a category

    Page<Product> findAllInSubCategory(Integer subCategoryId, Pageable pageable);

    // increase stock
    void increaseStock(String productId, int amount);

    // decrease stock
    void decreaseStock(String productId, int amount);

    Product offSale(String productId);

    Product onSale(String productId);

    Product update(Product product);
    Product save(Product product);

    void delete(String productId);

    List<Product> findAll();

    List<Product> search(String keyword);


}
