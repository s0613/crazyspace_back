package com.crazyspace_edu.api.domain.ai;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QConversation is a Querydsl query type for Conversation
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QConversation extends EntityPathBase<Conversation> {

    private static final long serialVersionUID = -331402936L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QConversation conversation = new QConversation("conversation");

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ListPath<ChatMessage, QChatMessage> messages = this.<ChatMessage, QChatMessage>createList("messages", ChatMessage.class, QChatMessage.class, PathInits.DIRECT2);

    public final com.crazyspace_edu.api.domain.user.QUser user;

    public QConversation(String variable) {
        this(Conversation.class, forVariable(variable), INITS);
    }

    public QConversation(Path<? extends Conversation> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QConversation(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QConversation(PathMetadata metadata, PathInits inits) {
        this(Conversation.class, metadata, inits);
    }

    public QConversation(Class<? extends Conversation> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new com.crazyspace_edu.api.domain.user.QUser(forProperty("user")) : null;
    }

}

