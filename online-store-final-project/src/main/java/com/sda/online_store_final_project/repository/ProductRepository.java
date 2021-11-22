package com.sda.online_store_final_project.repository;

import com.sda.online_store_final_project.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductRepository extends JpaRepository<Product, String>{
    // One product
    Product findFirstByProductId(String id);
    // onsale product
    Page<Product> findAllByProductStatusOrderByProductIdAsc(Integer productStatus, Pageable pageable);

    // product in one category
    Page<Product> findAllByCategoryTypeOrderByProductIdAsc(Integer categoryType, Pageable pageable);

    Page<Product> findAllByOrderByProductId(Pageable pageable);

}
