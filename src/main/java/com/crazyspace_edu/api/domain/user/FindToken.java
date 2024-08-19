package com.crazyspace_edu.api.domain.user;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class FindToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String token;

    @Column(nullable = false)
    private LocalDateTime expiryDate;

    @Builder
    public FindToken(String token) {
        this.id = id;
        this.token = token;
        this.expiryDate = LocalDateTime.now().plusHours(24);
    }
}
