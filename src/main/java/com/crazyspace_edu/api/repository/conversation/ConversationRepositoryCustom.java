package com.crazyspace_edu.api.repository.conversation;

import java.util.List;

public interface ConversationRepositoryCustom {
    List<Long> conversationIdFindByUserId(Long userId);
}
