package com.elice.ggorangjirang.deliveries;

import com.elice.ggorangjirang.deliveries.controller.DeliveryController;
import com.elice.ggorangjirang.deliveries.dto.DeliveryDto;
import com.elice.ggorangjirang.deliveries.dto.DeliveryStatusDto;
import com.elice.ggorangjirang.deliveries.entity.Deliveries;
import com.elice.ggorangjirang.deliveries.repository.DeliveryRepository;
import com.elice.ggorangjirang.deliveries.service.DeliveryService;
import com.elice.ggorangjirang.global.email.service.EmailService;
import com.elice.ggorangjirang.users.entity.Users;
import com.elice.ggorangjirang.orders.entity.Order;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DeliveryControllerTest {

    @InjectMocks
    private DeliveryController deliveryController;

    @Mock
    private DeliveryService deliveryService;

    @Mock
    private DeliveryRepository deliveryRepository;

    @Mock
    private EmailService emailService;

    @Test
    public void testAddDelivery(){
        DeliveryDto deliveryDto = new DeliveryDto();
        when(deliveryService.addDelivery(deliveryDto)).thenReturn(1L);
        ResponseEntity<Long> response = deliveryController.addDelivery(deliveryDto);
        assertThat(response.getBody()).isEqualTo(1L);
    }

    @Test
    public void testViewDelivery(){
        Deliveries delivery = new Deliveries();
        when(deliveryService.getDeliveryById(1L)).thenReturn(delivery);
        ResponseEntity<Deliveries> response = deliveryController.viewDelivery(1L);
        assertThat(response.getBody()).isEqualTo(delivery);
    }

    @Test
    public void testUpdateDeliveriesStatusByOrderId() {
        // Setup mock data
        Users user = mock(Users.class);
        when(user.getEmail()).thenReturn("test@example.com");

        Order order = mock(Order.class);
        when(order.getOrderNumber()).thenReturn("12345");
        when(order.getUsers()).thenReturn(user);

        Deliveries delivery = new Deliveries();
        delivery.setOrder(order);

        List<Deliveries> deliveriesList = List.of(delivery);
        DeliveryStatusDto statusDto = new DeliveryStatusDto();
        statusDto.setStatus("DELIVERY_COMPLETE");

        // Mock repository behavior
        when(deliveryRepository.findByOrderId(1L)).thenReturn(deliveriesList);

        // Perform the test
        ResponseEntity<String> response = deliveryController.updateDeliveriesStatusByOrderId(1L, statusDto);

        // Verify the results
        assertThat(response.getBody()).isEqualTo("배송 상태 변경.");
    }
}
