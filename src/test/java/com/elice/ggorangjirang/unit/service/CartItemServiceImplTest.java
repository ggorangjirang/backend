package com.elice.ggorangjirang.unit.service;

import com.elice.ggorangjirang.carts.dto.CartItemResponse;
import com.elice.ggorangjirang.carts.entity.Cart;
import com.elice.ggorangjirang.carts.entity.CartItem;
import com.elice.ggorangjirang.carts.repository.CartItemRepository;
import com.elice.ggorangjirang.carts.repository.CartRepository;
import com.elice.ggorangjirang.carts.service.CartItemServiceImpl;
import com.elice.ggorangjirang.products.entity.Product;
import com.elice.ggorangjirang.products.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
@Transactional
class CartItemServiceImplTest {

    @Mock
    private CartItemRepository cartItemRepository;

    @Mock
    private CartRepository cartRepository;

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private CartItemServiceImpl cartItemService;

    private Cart cart;
    private Product product;
    private CartItem cartItem;

    @BeforeEach
    public void setup() {
        // Mock 객체 초기화
        cart = Cart.builder().id(1L).build();
        product = Product.builder().id(1L).name("Test Product").price(15000).discountRate(10).build();
        cartItem = CartItem.builder().id(1L).cart(cart).product(product).quantity(1).build();
    }

    @Test
    @DisplayName("장바구니 상품 추가")
    public void addCartItem() {
        // given 장바구니에 해당 상품이 없다고 가정
        when(productRepository.findById(product.getId())).thenReturn(Optional.of(product));
        when(cartRepository.findById(cart.getId())).thenReturn(Optional.of(cart));
        when(cartItemRepository.findByCartIdAndProductId(cart.getId(), product.getId())).thenReturn(Optional.empty());
        when(cartItemRepository.save(any(CartItem.class))).thenReturn(cartItem);

        // when
        CartItemResponse response = cartItemService.addCartItem(cart.getId(), product.getId(), 2);

        // then
        assertNotNull(response);
        assertEquals(cartItem.getId(), response.getId());
        assertEquals(product.getId(), response.getProductId());
    }

    @Test
    @DisplayName("장바구니 상품 목록 조회 - 페이지네이션")
    public void getCartItemsByCartId_pagination() {
        // given
        List<CartItem> cartItems = new ArrayList<>();
        cartItems.add(cartItem);

        PageRequest pageRequest = PageRequest.of(0, 10);
        Page<CartItem> cartItemsPage = new PageImpl<>(cartItems, pageRequest, 1);

        when(cartItemRepository.findByCartId(cart.getId(), pageRequest)).thenReturn(cartItemsPage);

        // when
        Page<CartItemResponse> responsePage = cartItemService.getCartItemsByCartId(cart.getId(), 0, 10);

        // then
        assertEquals(1, responsePage.getTotalElements());
        assertEquals(cartItem.getId(), responsePage.getContent().get(0).getId());
        assertEquals(product.getId(), responsePage.getContent().get(0).getProductId());
    }

    @Test
    @DisplayName("장바구니 상품 목록 조회 - 전체")
    public void getCartItemListByCartId() {
        // given
        List<CartItem> cartItems = new ArrayList<>();
        cartItems.add(cartItem);

        when(cartItemRepository.findCartItemListByCartId(cart.getId())).thenReturn(cartItems);

        // when
        List<CartItemResponse> responseList = cartItemService.getCartItemListByCartId(cart.getId());

        // then
        assertEquals(1, responseList.size());
        assertEquals(cartItem.getId(), responseList.get(0).getId());
        assertEquals(product.getId(), responseList.get(0).getProductId());
    }

    @Test
    @DisplayName("장바구니 상품 수량 업데이트")
    public void updateCartItem() {
        // given
        int updatedQuantity = 3;
        cartItem.setQuantity(1);
        when(cartItemRepository.findById(cartItem.getId())).thenReturn(Optional.of(cartItem));
        when(cartItemRepository.save(any(CartItem.class))).thenReturn(cartItem);

        // when
        CartItemResponse response = cartItemService.updateCartItem(cartItem.getId(), updatedQuantity);

        // then
        assertNotNull(response);
        assertEquals(cartItem.getId(), response.getId());
        assertEquals(updatedQuantity, response.getQuantity());
    }

    @Test
    @DisplayName("장바구니 상품 삭제")
    public void deleteCartItem() {
        // given
        when(cartItemRepository.existsById(cartItem.getId())).thenReturn(true);

        // when
        assertDoesNotThrow(() -> cartItemService.deleteCartItem(cartItem.getId()));

        // then
        verify(cartItemRepository, times(1)).deleteById(cartItem.getId());
    }
}
