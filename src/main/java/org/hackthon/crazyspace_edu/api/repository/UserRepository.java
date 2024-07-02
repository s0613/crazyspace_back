package org.hackthon.crazyspace_edu.api.repository;

import org.hackthon.crazyspace_edu.api.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
}
