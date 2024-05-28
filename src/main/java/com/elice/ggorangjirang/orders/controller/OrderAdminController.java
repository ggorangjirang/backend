package com.elice.ggorangjirang.orders.controller;



import com.elice.ggorangjirang.deliveries.service.DeliveryService;
import com.elice.ggorangjirang.orders.dto.OrderResponse;
import com.elice.ggorangjirang.orders.service.OrderItemService;
import com.elice.ggorangjirang.orders.service.OrderService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/order")
public class OrderAdminController {
  private final OrderService orderService;
  private final OrderItemService orderItemService;
  private final DeliveryService deliveryService;


  // 회원 주문 목록
  @GetMapping("")
  public String findOrders(Model model) {
    List<OrderResponse> orderResponses = orderService.findAll()
        .stream()
        .map(OrderResponse::new)
        .toList();

    model.addAttribute("orders", orderResponses);

    return "admin-orders/admin-orders";
  }

  // 회원 주문 취소
  @DeleteMapping("/{id}")
  public String deleteUsersOrder(@PathVariable(value = "id") Long id, Model model) {
    try {
      orderService.cancel(id);
      return "redirect:admin-orders/admin-orders"; // 삭제 후 목록 페이지로 리디렉션
    } catch (IllegalStateException e) {
      model.addAttribute("error", e.getMessage());
      return "error"; // 에러 페이지 템플릿을 반환
    }
  }


}
