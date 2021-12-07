package com.sda.online_store_final_project.repository;

import com.sda.online_store_final_project.entity.OrderLine;
import org.springframework.data.jpa.repository.JpaRepository;


public interface OrderLineRepository extends JpaRepository<OrderLine, Long> {

}
