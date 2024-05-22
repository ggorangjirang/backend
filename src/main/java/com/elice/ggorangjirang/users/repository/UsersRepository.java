package com.elice.ggorangjirang.users.repository;

import com.elice.ggorangjirang.users.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users, Long> {
    Optional<Users> findByEmail(String email);

    Optional<Users> findByRefreshToken(String refreshToken);

    Optional<Users> findBySocialId(String socialId);
}
