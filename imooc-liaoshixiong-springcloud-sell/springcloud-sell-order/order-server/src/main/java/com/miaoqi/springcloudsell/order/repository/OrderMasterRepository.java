package com.miaoqi.springcloudsell.order.repository;

import com.miaoqi.springcloudsell.order.pojo.OrderMaster;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by 廖师兄
 * 2017-12-10 16:11
 */
public interface OrderMasterRepository extends JpaRepository<OrderMaster, String> {
}
