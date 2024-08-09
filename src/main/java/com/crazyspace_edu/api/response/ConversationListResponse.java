package com.crazyspace_edu.api.response;
import java.time.LocalDateTime;


public class ConversationListResponse {

    public ConversationListResponse() {}

    public ConversationListResponse(LocalDateTime createdAt, String title, Long id) {
        this.createdAt = createdAt;
        this.title = title;
        this.id = id;
    }

    LocalDateTime createdAt;
    String title;
    Long id;

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public String getTitle() {
        return title;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
