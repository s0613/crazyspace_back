package com.crazyspace_edu.api.repository.conversation;

import com.crazyspace_edu.api.domain.ai.ChatMessage;
import com.crazyspace_edu.api.domain.ai.QConversation;
import com.crazyspace_edu.api.response.ConversationDTO;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ConversationRepositoryRepositoryImpl implements ConversationRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<Long> findByUserId(Long userId) {
        QConversation conversation = QConversation.conversation;

        return queryFactory
                .select(conversation.id)
                .from(conversation)
                .where(conversation.user.id.eq(userId))
                .fetch();
    }
}