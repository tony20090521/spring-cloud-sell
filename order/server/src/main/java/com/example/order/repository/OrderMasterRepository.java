package com.example.order.repository;


import com.example.order.dataobject.OrderMaster;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by 廖师兄
 * 2017-12-10 16:11
 */
public interface OrderMasterRepository extends JpaRepository<OrderMaster, String> {
}
