package com.sda.online_store_final_project.service.impl;

import com.sda.online_store_final_project.entity.OrderLine;
import com.sda.online_store_final_project.entity.OrderMain;
import com.sda.online_store_final_project.entity.Product;
import com.sda.online_store_final_project.enums.OrderStatusEnum;
import com.sda.online_store_final_project.enums.ResultEnum;
import com.sda.online_store_final_project.exception.MyException;
import com.sda.online_store_final_project.repository.OrderLineRepository;
import com.sda.online_store_final_project.repository.OrderRepository;
import com.sda.online_store_final_project.repository.ProductRepository;
import com.sda.online_store_final_project.repository.UserAccountRepository;
import com.sda.online_store_final_project.service.OrderService;
import com.sda.online_store_final_project.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    UserAccountRepository userAccountRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    ProductService productService;
    @Autowired
    OrderLineRepository orderLineRepository;

    @Override
    public Page<OrderMain> findAll(Pageable pageable) {
        return orderRepository.findAllByOrderByOrderStatusAscCreateTimeAsc(pageable);
    }

    @Override
    public Page<OrderMain> findByStatus(Integer status, Pageable pageable) {
        return orderRepository.findAllByOrderStatusOrderByCreateTimeAsc(status, pageable);
    }

    @Override
    public Page<OrderMain> findByCustomerEmail(String email, Pageable pageable) {
        return orderRepository.findAllByCustomerEmailOrderByOrderStatusAscCreateTimeAsc(email, pageable);
    }

    @Override
    public Page<OrderMain> findByCustomerPhone(String phone, Pageable pageable) {
        return orderRepository.findAllByCustomerPhoneOrderByOrderStatusAscCreateTimeAsc(phone, pageable);
    }

    @Override
    public OrderMain findOne(Long orderId) {
        OrderMain orderMain = orderRepository.findByOrderId(orderId);
        return orderMain;
    }

    @Override
    @Transactional
    public void finish(Long orderId) {
        OrderMain orderMain = findOne(orderId);

        orderMain.setOrderStatus(OrderStatusEnum.FINISHED.getCode());
        orderRepository.save(orderMain);
    }

    @Override
    @Transactional
    public void cancel(Long orderId) {
        OrderMain orderMain = findOne(orderId);
        if(!orderMain.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
            throw new MyException(ResultEnum.ORDER_STATUS_ERROR);
        }

        orderMain.setOrderStatus(OrderStatusEnum.CANCELED.getCode());
        orderRepository.save(orderMain);

        // Restore Stock
        Iterable<OrderLine> products = orderMain.getProducts();
        for(OrderLine productInOrder : products) {
            Product product = productRepository.findFirstByProductId(productInOrder.getProductId());
            if(product != null) {
                productService.increaseStock(productInOrder.getProductId(), productInOrder.getProductQuantity());
            }
        }
    }
}
