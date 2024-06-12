package com.elice.ggorangjirang.users.repository;

import com.elice.ggorangjirang.users.entity.Users;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, Long> {
    Optional<Users> findByEmail(String email);

    Optional<Users> findById(Long id);

    Optional<Users> findByRefreshToken(String refreshToken);

    Optional<Users> findBySocialId(String socialId);

    Optional<Users> findByEmailOrSocialId(String email, String socialId);

    Optional<Users> findByVerificationToken_Token(String token);
}
