package com.elice.ggorangjirang.unit.service;

import com.elice.ggorangjirang.carts.dto.CartDto;
import com.elice.ggorangjirang.carts.entity.Cart;
import com.elice.ggorangjirang.carts.entity.CartItem;
import com.elice.ggorangjirang.carts.exception.CartNotFoundException;
import com.elice.ggorangjirang.carts.repository.CartItemRepository;
import com.elice.ggorangjirang.carts.repository.CartRepository;
import com.elice.ggorangjirang.carts.service.CartService;
import com.elice.ggorangjirang.products.entity.Product;
import com.elice.ggorangjirang.users.entity.Users;
import com.elice.ggorangjirang.users.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class CartServiceTest {

    @Mock
    private CartRepository cartRepository;

    @Mock
    private CartItemRepository cartItemRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private CartService cartService;

    private Users user;
    private Cart cart;
    private CartItem cartItem;
    private Product product;

    @BeforeEach
    public void setUp() {
        // Mock 객체 초기화
        user = Users.builder().id(1L).email("test@test.com").build();
        cart = Cart.builder().id(1L).user(user).build();
        product = Product.builder().id(1L).name("Test Product").price(100).discountRate(10).build();
        cartItem = CartItem.builder().id(1L).cart(cart).product(product).quantity(1).build();
    }

    @Test
    @DisplayName("장바구니 생성")
    public void createCartForUser() {
        // given
        when(cartRepository.findByUserId(user.getId())).thenReturn(Optional.empty());

        // when
        cartService.createCartForUser(user);

        // then
        verify(cartRepository, times(1)).save(any(Cart.class));
    }

    @Test
    @DisplayName("장바구니 조회")
    public void getCartItems() {
        // given
        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));
        when(cartRepository.findByUserId(user.getId())).thenReturn(Optional.of(cart));
        Pageable pageable = PageRequest.of(0, 10);
        Page<CartItem> cartItemsPage = new PageImpl<>(Collections.singletonList(cartItem));
        when(cartItemRepository.findByCartId(cart.getId(), pageable)).thenReturn(cartItemsPage);

        // when
        CartDto cartDto = cartService.getCartItems(user.getEmail(), 0, 10);

        // then
        assertNotNull(cartDto);
        assertEquals(cart.getId(), cartDto.getCartId());
        assertEquals(1, cartDto.getCartItems().getTotalElements());
    }

    @Test
    @DisplayName("장바구니 조회 UserNotFound 예외발생")
    public void getCartItemsUserNotFound() {
        // given
        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.empty());

        // when/then
        assertThrows(IllegalArgumentException.class, () -> cartService.getCartItems(user.getEmail(), 0, 10));
    }

    @Test
    @DisplayName("장바구니 조회 CartNotFound 예외발생")
    public void getCartItemsCartNotFound() {
        // given
        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));
        when(cartRepository.findByUserId(user.getId())).thenReturn(Optional.empty());

        // when/then
        assertThrows(CartNotFoundException.class, () -> cartService.getCartItems(user.getEmail(), 0, 10));
    }
}
