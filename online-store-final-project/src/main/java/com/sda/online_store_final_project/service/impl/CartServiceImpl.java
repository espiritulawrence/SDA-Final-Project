package com.sda.online_store_final_project.service.impl;

import com.sda.online_store_final_project.dto.Item;
import com.sda.online_store_final_project.entity.OrderLine;
import com.sda.online_store_final_project.entity.OrderMain;
import com.sda.online_store_final_project.entity.Product;
import com.sda.online_store_final_project.entity.UserAccount;
import com.sda.online_store_final_project.enums.ProductStatusEnum;
import com.sda.online_store_final_project.enums.ResultEnum;
import com.sda.online_store_final_project.exception.MyException;
import com.sda.online_store_final_project.form.ItemForm;
import com.sda.online_store_final_project.repository.OrderRepository;
import com.sda.online_store_final_project.service.CartService;
import com.sda.online_store_final_project.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;


@Service
public class CartServiceImpl implements CartService {
    @Autowired
    ProductService productService;
    @Autowired
    OrderRepository orderRepository;

    private Map<String, Item> map = new LinkedHashMap<>();

    @Override
    public void addItem(ItemForm itemForm) {
        Product product = productService.findOne(itemForm.getProductId());
        if (product.getProductStatus() == ProductStatusEnum.DOWN.getCode()) {
            throw new MyException(ResultEnum.PRODUCT_OFF_SALE);
        }

        // Check if product is in the cart
        if(map.containsKey(itemForm.getProductId())){
            // Update quantity
            Integer old = map.get(itemForm.getProductId()).getQuantity();
            itemForm.setQuantity(old + itemForm.getQuantity());
        }

        map.put(itemForm.getProductId(), new Item(product, itemForm.getQuantity()));
    }

    @Override
    public void removeItem(String productId) {
        if (!map.containsKey(productId)) throw new MyException(ResultEnum.PRODUCT_NOT_IN_CART);
        map.remove(productId);
    }

    @Override
    public void updateQuantity(String productId, Integer quantity) {
        Item item = map.get(productId);
        Integer max = item.getProduct().getProductStock();
        if(quantity > 0) {
            item.setQuantity(quantity > max ? max : quantity);
        }
    }

    @Override
    public Collection<Item> findAll() {
        return map.values();
    }

    @Override
    @Transactional
    public void checkout(UserAccount userAccount) {
        OrderMain orderMain = new OrderMain(userAccount);
        for (String productId : map.keySet()) {
            Item item = map.get(productId);
            OrderLine productInOrder = new OrderLine(item.getProduct(), item.getQuantity());
            productInOrder.setOrderMain(orderMain);
            orderMain.getProducts().add(productInOrder);
            productService.decreaseStock(productId, item.getQuantity());
        }
        orderMain.setOrderAmount(getTotal());
        orderRepository.save(orderMain);
        map.clear();
    }

    @Override
    public BigDecimal getTotal() {
        Collection<Item> items = findAll();
        BigDecimal total = new BigDecimal(0);
        for (Item item : items) {
            BigDecimal price = item.getProduct().getProductPrice();
            BigDecimal quantity = new BigDecimal(item.getQuantity());
            total = total.add(price.multiply(quantity));
        }
        return total;
    }
}
