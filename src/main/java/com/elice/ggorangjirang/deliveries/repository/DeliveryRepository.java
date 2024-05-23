package com.elice.ggorangjirang.deliveries.repository;

import com.elice.ggorangjirang.deliveries.entity.Deliveries;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryRepository extends JpaRepository<Deliveries, Long> {

  List<Deliveries> findByOrderId(Long orderId);

}
