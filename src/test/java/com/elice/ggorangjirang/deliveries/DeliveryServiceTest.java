package com.elice.ggorangjirang.deliveries;

import com.elice.ggorangjirang.deliveries.Mapper.DeliveryMapper;
import com.elice.ggorangjirang.deliveries.dto.DeliveryDto;
import com.elice.ggorangjirang.deliveries.entity.Deliveries;
import com.elice.ggorangjirang.deliveries.repository.DeliveryRepository;
import com.elice.ggorangjirang.deliveries.service.DeliveryService;
import com.elice.ggorangjirang.orders.entity.Order;
import com.elice.ggorangjirang.orders.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DeliveryServiceTest {

    @Mock
    private DeliveryMapper deliveryMapper;

    @Mock
    private DeliveryRepository deliveryRepository;

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private DeliveryService deliveryService;

    @Test
    public void testGetOrderWithItems() {
        Order order = mock(Order.class);
        when(orderRepository.findById(1L)).thenReturn(Optional.of(order));

        Order result = deliveryService.getOrderWithItems(1L);

        assertThat(result).isEqualTo(order);
    }

    @Test
    public void testAddDelivery() {
        DeliveryDto deliveryDto = new DeliveryDto();
        Deliveries delivery = new Deliveries();
        Deliveries savedDelivery = new Deliveries();
        savedDelivery.setId(1L);
        when(deliveryMapper.toEntity(deliveryDto)).thenReturn(delivery);
        when(deliveryRepository.save(delivery)).thenReturn(savedDelivery);

        Long deliveryId = deliveryService.addDelivery(deliveryDto);

        assertThat(deliveryId).isEqualTo(1L);
    }

    @Test
    public void testGetDeliveryById() {
        Deliveries delivery = new Deliveries();
        when(deliveryRepository.findById(1L)).thenReturn(Optional.of(delivery));

        Deliveries result = deliveryService.getDeliveryById(1L);

        assertThat(result).isEqualTo(delivery);
    }

    @Test
    public void testUpdateDeliveryStatus() {
        Deliveries delivery = new Deliveries();
        when(deliveryRepository.findById(1L)).thenReturn(Optional.of(delivery));

        Deliveries result = deliveryService.updateDeliveryStatus(1L, "DELIVERING");

        assertThat(result.getStatus()).isEqualTo("DELIVERING");
    }

    @Test
    public void testGetDeliveriesByOrderId() {
        List<Deliveries> deliveriesList = List.of(new Deliveries());
        when(deliveryRepository.findByOrderId(1L)).thenReturn(deliveriesList);

        List<Deliveries> result = deliveryService.getDeliveriesByOrderId(1L);

        assertThat(result).isEqualTo(deliveriesList);
    }
}
