package com.sda.online_store_final_project.repository;

import com.sda.online_store_final_project.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface ProductRepository extends JpaRepository<Product, String>{
    // One product
    Product findFirstByProductId(String id);

    // onsale product
    Page<Product> findAllByProductStatusOrderByProductIdAsc(Integer productStatus, Pageable pageable);

    // product in one category

    Page<Product> findAllByCategoryIdOrderByProductIdAsc(Integer categoryId, Pageable pageable);

    // product in one subcategory
    Page<Product> findAllBySubCategoryIdOrderByProductIdAsc(Integer subCategoryId, Pageable pageable);

    Page<Product> findAllByOrderByProductId(Pageable pageable);


//    @Query(value = "SELECT p FROM Product p WHERE p.productName LIKE %?1%")
//    Product findByKeyword(String keyword);

    @Query(value = "SELECT * FROM product WHERE "
            + "MATCH(PRODUCT_NAME,PRODUCT_DESCRIPTION) "
            + "AGAINST (?1)",
            nativeQuery = true)
    public List<Product> findByKeyword(String keyword);
}
