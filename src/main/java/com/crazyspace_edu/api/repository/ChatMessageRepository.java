package com.crazyspace_edu.api.repository;

import com.crazyspace_edu.api.domain.ai.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {
//    List<ChatMessage> findByUserId(Long userId);
}
