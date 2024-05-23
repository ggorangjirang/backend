package com.elice.ggorangjirang.carts.service;

import com.elice.ggorangjirang.carts.dto.CartDto;
import com.elice.ggorangjirang.carts.entity.Cart;
import com.elice.ggorangjirang.carts.repository.CartRepository;
import com.elice.ggorangjirang.products.entity.Product;
import com.elice.ggorangjirang.products.repository.ProductRepository;
// import com.elice.ggorangjirang.users.repository.UsersRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CartService {

    // CartRepository 인스턴스 필드
    private final CartRepository cartRepository;

    // ProductRepository 인스턴스 필드
    private final ProductRepository productRepository;

    // UsersRepository 인스턴스 필드 (현재 주석 처리됨)
    // private final UsersRepository userRepository;

    @Autowired
    public CartService(CartRepository cartRepository, ProductRepository productRepository) {
        // UsersRepository userRepository
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
        // this.userRepository = userRepository;
    }

    // 카트에 항목 추가
    public void addCart(CartDto cartDto) {
        Cart cart = CartDto.toEntity(cartDto);
        cartRepository.save(cart);
    }

    // 사용자 ID로 카트 항목 조회
    public List<CartDto> getCartItemsByUserId(Long userId) {
        // 특정 사용자의 카트 항목 조회
        // List<Cart> cartEntities = cartRepository.findByUserId(userId);
        List<Cart> cartEntities = cartRepository.findAll(); // 일단 모든 카트를 가져오는 것으로 수정
        return cartEntities.stream()
            .map(cart -> {
                Optional<Product> productOptional = productRepository.findById(cart.getProduct().getId());
                Product product = productOptional.orElseThrow(() -> new EntityNotFoundException("Product not found"));
                return CartDto.fromEntity(cart, product.getName(), product.getPrice());
            })
            .collect(Collectors.toList());
    }

    // 여러 개의 카트 항목 삭제
    public void deleteCartItems(List<Long> itemIds) {
        for (Long itemId : itemIds) {
            cartRepository.deleteById(itemId);
        }
    }

    // 특정 카트 항목의 수량 증가
    public CartDto increaseItemCount(Long itemId) {
        Optional<Cart> opCart = cartRepository.findById(itemId);
        if (opCart.isPresent()) {
            Cart cart = opCart.get();
            cart.setCartCount(cart.getCartCount() + 1);
            Cart updatedCart = cartRepository.save(cart);
            return CartDto.fromEntity(updatedCart);
        } else {
            throw new EntityNotFoundException("카트 아이템을 찾을 수 없습니다.");
        }
    }

    // 특정 카트 항목의 수량 감소
    public CartDto decreaseItemCount(Long itemId) {
        Optional<Cart> opCart = cartRepository.findById(itemId);
        if (opCart.isPresent()) {
            Cart cart = opCart.get();
            if (cart.getCartCount() > 1) {
                cart.setCartCount(cart.getCartCount() - 1);
                Cart updatedCart = cartRepository.save(cart);
                return CartDto.fromEntity(updatedCart);
            } else {
                throw new IllegalArgumentException("감소 불가");
            }
        } else {
            throw new EntityNotFoundException("카트 아이템을 찾을 수 없습니다.");
        }
    }
}

