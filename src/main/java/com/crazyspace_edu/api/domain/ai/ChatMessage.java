package com.crazyspace_edu.api.domain.ai;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class ChatMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long  id;

    @ManyToOne(targetEntity = Conversation.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "conversation_id")
    private Conversation conversation;

    @Column(nullable = false)
    private String role; // User 또는 AI인지 확인하는 용도

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Builder
    public ChatMessage(Conversation conversation, String role, String content) {
        this.conversation = conversation;
        this.role = role;
        this.content = content;
        this.createdAt = LocalDateTime.now();
    }

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}
