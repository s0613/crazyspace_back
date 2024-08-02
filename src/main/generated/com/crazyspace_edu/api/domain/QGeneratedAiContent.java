package com.crazyspace_edu.api.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QGeneratedAiContent is a Querydsl query type for GeneratedAiContent
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QGeneratedAiContent extends EntityPathBase<GeneratedAiContent> {

    private static final long serialVersionUID = -1186775677L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QGeneratedAiContent generatedAiContent = new QGeneratedAiContent("generatedAiContent");

    public final DateTimePath<java.time.LocalDateTime> chg_dt = createDateTime("chg_dt", java.time.LocalDateTime.class);

    public final StringPath content = createString("content");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final DateTimePath<java.time.LocalDateTime> reg_dt = createDateTime("reg_dt", java.time.LocalDateTime.class);

    public final StringPath title = createString("title");

    public final com.crazyspace_edu.api.domain.user.QUser user;

    public QGeneratedAiContent(String variable) {
        this(GeneratedAiContent.class, forVariable(variable), INITS);
    }

    public QGeneratedAiContent(Path<? extends GeneratedAiContent> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QGeneratedAiContent(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QGeneratedAiContent(PathMetadata metadata, PathInits inits) {
        this(GeneratedAiContent.class, metadata, inits);
    }

    public QGeneratedAiContent(Class<? extends GeneratedAiContent> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new com.crazyspace_edu.api.domain.user.QUser(forProperty("user")) : null;
    }

}

