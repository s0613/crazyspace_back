package com.crazyspace_edu.api.repository.conversation;

import com.crazyspace_edu.api.domain.ai.ChatMessage;

import java.util.List;

public interface ConversationRepositoryCustom {
    List<Long> findByUserId(Long userId);
}
