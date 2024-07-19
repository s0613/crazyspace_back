package org.crazyspace_edu.api.domain;

import jakarta.persistence.*;
import lombok.Data;
import org.crazyspace_edu.api.domain.user.User;

@Data
@Entity
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

}
