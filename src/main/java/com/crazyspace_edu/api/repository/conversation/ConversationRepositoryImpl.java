package com.crazyspace_edu.api.repository.conversation;

import com.crazyspace_edu.api.domain.ai.Conversation;
import com.crazyspace_edu.api.domain.ai.QConversation;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ConversationRepositoryImpl implements ConversationRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<Long> conversationIdFindByUserId(Long userId) {
        QConversation conversation = QConversation.conversation;

        return queryFactory
                .select(conversation.id)
                .from(conversation)
                .where(conversation.user.id.eq(userId))
                .fetch();
    }
}