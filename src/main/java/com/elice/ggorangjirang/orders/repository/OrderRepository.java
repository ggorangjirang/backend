package com.elice.ggorangjirang.orders.repository;

import com.elice.ggorangjirang.orders.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long> {

}
