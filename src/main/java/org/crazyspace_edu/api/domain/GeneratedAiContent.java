package org.crazyspace_edu.api.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.crazyspace_edu.api.domain.user.User;

import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
public class GeneratedAiContent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;
    @Column
    private String content;

    @ManyToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private User user;

    @Column(nullable = false)
    private LocalDateTime reg_dt; // @Column 추가

    @Column(nullable = false)
    private LocalDateTime chg_dt; // @Column 추가

    @Builder
    public GeneratedAiContent(String title, String content, User user) {
        this.title = title;
        this.content = content;
        this.user = user;
        this.reg_dt = LocalDateTime.now();
        this.chg_dt = LocalDateTime.now();
    }

    @PrePersist
    protected void onCreate() {
        this.reg_dt = LocalDateTime.now();
        this.chg_dt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.chg_dt = LocalDateTime.now();
    }
}
