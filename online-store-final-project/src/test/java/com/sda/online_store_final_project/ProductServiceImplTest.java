package com.sda.online_store_final_project;

import com.sda.online_store_final_project.entity.Product;
import com.sda.online_store_final_project.enums.ProductStatusEnum;
import com.sda.online_store_final_project.service.ProductService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ProductServiceImplTest {

    @Autowired
    ProductService productService;

    @Test
    public void findOne() {
        Product product = productService.findOne("MS0001");
        Assert.assertEquals("MS0001",product.getProductId());
    }

    @Test
    public void findUpAll() {
    }

    @Test
    public void findAll() {
    }

    @Test
    public void findAllInCategory() {
    }

    @Test
    public void increaseStock() {
        Integer old = productService.findOne("MS0001").getProductStock();
        productService.increaseStock("MS0001", 50);
        Integer added = productService.findOne("MS0001").getProductStock();
        Assert.assertEquals(50, added - old);
    }

    @Test
    public void decreaseStock() {
        Integer old = productService.findOne("MS0001").getProductStock();
        productService.decreaseStock("MS0001", 50);
        Integer decreased = productService.findOne("MS0001").getProductStock();
        Assert.assertEquals(50,  old - decreased);
    }

    @Test
    public void offSale() {
        productService.offSale("PR0010");
        Assert.assertEquals(ProductStatusEnum.DOWN.getCode(),  productService.findOne("C0003").getProductStatus());
    }

    @Test
    public void onSale() {
        productService.onSale("MS0001");
        Assert.assertEquals(ProductStatusEnum.UP.getCode(),  productService.findOne("C0003").getProductStatus());
    }
}