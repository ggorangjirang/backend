package com.elice.ggorangjirang.carts.service;

import com.elice.ggorangjirang.carts.repository.CartItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CartCleanupService {

    private final CartItemRepository cartItemRepository;

    @Scheduled(cron = "0 0 0 * * ?") // 매일 자정에 실행
    @Transactional
    public void cleanupOldCartItems() {
        LocalDateTime deletionThreshold = LocalDateTime.now().minusMonths(1);
        cartItemRepository.deleteOldCartItems(deletionThreshold);
    }
}
