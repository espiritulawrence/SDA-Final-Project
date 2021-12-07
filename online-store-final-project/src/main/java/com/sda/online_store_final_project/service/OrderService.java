package com.sda.online_store_final_project.service;

import com.sda.online_store_final_project.entity.OrderMain;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public interface OrderService {
    Page<OrderMain> findAll(Pageable pageable);

    Page<OrderMain> findByStatus(Integer status, Pageable pageable);

    Page<OrderMain> findByCustomerEmail(String email, Pageable pageable);

    Page<OrderMain> findByCustomerPhone(String phone, Pageable pageable);

    OrderMain findOne(Long orderId);



    void finish(Long orderId);

    void  cancel(Long orderId);

}
