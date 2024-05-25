package com.elice.ggorangjirang.deliveries.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import com.elice.ggorangjirang.deliveries.dto.DeliveryDto;
import com.elice.ggorangjirang.deliveries.dto.DeliveryStatusDto;
import com.elice.ggorangjirang.deliveries.entity.Deliveries;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import com.elice.ggorangjirang.deliveries.service.DeliveryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
public class DeliveryController {

  private final DeliveryService deliveryService;



  @PostMapping("/deliveries")
  public ResponseEntity<String> addDelivery(@RequestBody DeliveryDto deliveryDto) {
    deliveryService.addDelivery(deliveryDto);
    return ResponseEntity.ok("배송 정보가 추가되었습니다.");
  }

  @GetMapping("/deliveries/{id}")
  public ResponseEntity<Deliveries> viewDelivery(@PathVariable long id) {
    Deliveries delivery = deliveryService.getDeliveryById(id);
    return ResponseEntity.ok(delivery);
  }

  @GetMapping("/admin/orders/{orderId}/deliveries")
  public ResponseEntity<List<Deliveries>> viewDeliveryByOrderId(@PathVariable long orderId) {
    List<Deliveries> deliveriesList = deliveryService.getDeliveriesByOrderId(orderId);
    return ResponseEntity.ok(deliveriesList);
  }

  @PatchMapping("/admin/deliveries/{id}/status")
  public ResponseEntity<String> updateDeliveriesStatus(@PathVariable long id, @RequestBody DeliveryStatusDto statusDto) {
    deliveryService.updateDeliveryStatus(id, statusDto.getStatus());
    return ResponseEntity.ok("배송 상태 변경.");
  }

   /* @PostMapping("/deliveries")
  public String addDelivery(@RequestBody DeliveryDto deliveryDto) {
    deliveryService.addDelivery(deliveryDto);
    return "/deliveries";
  }

 @GetMapping("/deliveries/{id}")
  public String viewDelivery(@PathVariable long id, Model model) {
    Deliveries delivery = deliveryService.getDeliveryById(id);
    model.addAttribute("delivery", delivery);
    return "deliveries";
  }

  // 특정 주문에 대한 모든 배송 정보 조회
  @GetMapping("/admin/orders/{orderId}/deliveries")
  public String viewDeliveryByOrderId(@PathVariable long orderId, Model model) {
    List<Deliveries> deliveriesList = deliveryService.getDeliveriesByOrderId(orderId);
    model.addAttribute("deliveries", deliveriesList);
    return "deliveries";
  }

  // 상태 업데이트
  @PatchMapping("/admin/deliveries/{id}/status")
  public String updateDeliveriesStatus(@PathVariable long id, @RequestBody DeliveryStatusDto statusDto) {
    deliveryService.updateDeliveryStatus(id, statusDto.getStatus());
    return "redirect:/admin/deliveries";
  }
*/
}
