package com.sda.online_store_final_project.service.impl;

import com.sda.online_store_final_project.entity.Product;
import com.sda.online_store_final_project.enums.ProductStatusEnum;
import com.sda.online_store_final_project.enums.ResultEnum;
import com.sda.online_store_final_project.exception.MyException;
import com.sda.online_store_final_project.repository.ProductRepository;
import com.sda.online_store_final_project.service.CategoryService;
import com.sda.online_store_final_project.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryService categoryService;

    @Override
    public Product findOne(String productId) {
        Product product = productRepository.findFirstByProductId(productId);
        return product;
    }

    @Override
    public Page<Product> findUpAll(Pageable pageable) {
        return productRepository.findAllByProductStatusOrderByProductIdAsc(ProductStatusEnum.UP.getCode(),pageable);
    }

    @Override
    public Page<Product> findAll(Pageable pageable) {

        return productRepository.findAllByOrderByProductId(pageable);
    }

    @Override
    public Page<Product> findAllInCategory(Integer categoryId, Pageable pageable) {
        return productRepository.findAllByCategoryIdOrderByProductIdAsc(categoryId, pageable);
    }
    @Override
    public Page<Product> findAllInSubCategory(Integer subCategoryId, Pageable pageable) {
        return productRepository.findAllBySubCategoryIdOrderByProductIdAsc(subCategoryId, pageable);
    }


    @Override
    @Transactional
    public void increaseStock(String productId, int amount) {
        Product product = findOne(productId);
        if (product == null) throw new MyException(ResultEnum.PRODUCT_NOT_EXIST);

        int update = product.getProductStock() + amount;
        product.setProductStock(update);
        productRepository.save(product);
    }

    @Override
    @Transactional
    public void decreaseStock(String productId, int amount) {
        Product product = findOne(productId);
        if (product == null) throw new MyException(ResultEnum.PRODUCT_NOT_EXIST);

        int update = product.getProductStock() - amount;

        product.setProductStock(update);
        productRepository.save(product);
    }

    @Override
    @Transactional
    public Product offSale(String productId) {
        Product product = findOne(productId);
        if (product == null) throw new MyException(ResultEnum.PRODUCT_NOT_EXIST);
        if (product.getProductStatus() == ProductStatusEnum.DOWN.getCode()) {
            throw new MyException(ResultEnum.PRODUCT_STATUS_ERROR);
        }
        return productRepository.save(product);
    }

    @Override
    @Transactional
    public Product onSale(String productId) {
        Product product = findOne(productId);
        if (product == null) throw new MyException(ResultEnum.PRODUCT_NOT_EXIST);

        product.setProductStatus(ProductStatusEnum.UP.getCode());
        return productRepository.save(product);
    }

    @Override
    public Product update(Product product) {

        categoryService.findByCategoryId(product.getCategoryId());
        if (product == null) throw new MyException(ResultEnum.PRODUCT_NOT_EXIST);

        return productRepository.save(product);
    }

    @Override
    public Product save(Product product) {

        return update(product);
    }

    @Override
    public void delete(String productId) {
        Product product = findOne(productId);
        if (product == null) throw new MyException(ResultEnum.PRODUCT_NOT_EXIST);

        productRepository.delete(product);

    }

    @Override
    public List<Product> findAll() {

        return productRepository.findAll();
    }

    @Override
    public List<Product> search(String keyword) {
        if (keyword != null) {
        }
        return productRepository.findByKeyword(keyword);
    }
}
