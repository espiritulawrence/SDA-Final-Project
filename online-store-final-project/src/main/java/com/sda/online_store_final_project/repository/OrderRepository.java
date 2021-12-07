package com.sda.online_store_final_project.repository;

import com.sda.online_store_final_project.entity.OrderMain;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface OrderRepository extends JpaRepository<OrderMain, Integer> {

    OrderMain findByOrderId(Long orderId);


    Page<OrderMain> findAllByOrderStatusOrderByCreateTimeAsc(Integer orderStatus, Pageable pageable);


    Page<OrderMain> findAllByCustomerEmailOrderByOrderStatusAscCreateTimeAsc(String customerEmail, Pageable pageable);

    Page<OrderMain> findAllByOrderByOrderStatusAscCreateTimeAsc( Pageable pageable);

    Page<OrderMain> findAllByCustomerPhoneOrderByOrderStatusAscCreateTimeAsc(String customerPhone, Pageable pageable);
}
