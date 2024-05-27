package com.elice.ggorangjirang.orders;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.elice.ggorangjirang.deliveries.entity.Deliveries;
import com.elice.ggorangjirang.orders.entity.Order;
import com.elice.ggorangjirang.orders.entity.OrderItem;
import com.elice.ggorangjirang.orders.entity.OrderStatus;
import com.elice.ggorangjirang.products.entity.Product;
import com.elice.ggorangjirang.users.entity.Users;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class OrderTest {

  private List<OrderItem> orderItems;
  private Deliveries delivery;

  @BeforeEach
  public void setup() {
    List<OrderItem> list = new ArrayList<>();

    // Product 객체 생성
    Product product1 = Product.builder()
        .name("Product 1")
        .description("Description 1")
        .price(20000)
        .expirationDate(LocalDate.now())
        .discountRate(0.1f)
        .stock(100)
        .imageUrl("img1.jpg")
        .build();

    Product product2 = Product.builder()
        .name("Product 2")
        .description("Description 2")
        .price(50000)
        .expirationDate(LocalDate.now())
        .discountRate(0.2f)
        .stock(200)
        .imageUrl("img2.jpg")
        .build();

    Product product3 = Product.builder()
        .name("Product 3")
        .description("Description 3")
        .price(10000)
        .expirationDate(LocalDate.now())
        .discountRate(0.15f)
        .stock(300)
        .imageUrl("img3.jpg")
        .build();

    // OrderItem 생성
    list.add(OrderItem.createOrderItem(product1, 20000, 10));
    list.add(OrderItem.createOrderItem(product2, 50000, 10));
    list.add(OrderItem.createOrderItem(product3, 10000, 10));
    orderItems = list;


    // 필요한 매개변수 설정
    String zipcode = "12345";
    String streetAddress = "Main St";
    String detailAddress = "Apt 101";
    String status = "Pending";
    String request = "Please leave at the front door.";
    LocalDate arrivalDate = LocalDate.now();
    int phoneNumber = 1000000000;
    String name = "받는사람";

    // Delivery 객체 생성
    Deliveries delivery = Deliveries.createDelivery(zipcode, streetAddress, detailAddress, status, request, arrivalDate, phoneNumber, name);

    // 출력 확인
    System.out.println(delivery);

  }

  @DisplayName("주문 객체 생성 테스트")
  @Test
  public void addOrderTest() {
    Users uesrs = new Users("테스트","test@test.com","1234");
    // when
    Order order = Order.createOrder(uesrs, delivery, orderItems);

    // then
    assertEquals(order.getDeliveries(), delivery);
    assertEquals(order.getOrderItems(), orderItems);
    assertEquals(order.getOrderStatus(), OrderStatus.ORDER);
  }
}


