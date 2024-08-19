package com.crazyspace_edu.api.repository;

import com.crazyspace_edu.api.domain.user.FindToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FindTokenRepository extends JpaRepository<FindToken, Long> {
    Optional<FindToken> findByToken(String token);
}
