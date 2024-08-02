package com.crazyspace_edu.api.domain.user;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = -213067763L;

    public static final QUser user = new QUser("user");

    public final StringPath birth = createString("birth");

    public final DateTimePath<java.time.LocalDateTime> chg_dt = createDateTime("chg_dt", java.time.LocalDateTime.class);

    public final ListPath<com.crazyspace_edu.api.domain.GeneratedAiContent, com.crazyspace_edu.api.domain.QGeneratedAiContent> contents = this.<com.crazyspace_edu.api.domain.GeneratedAiContent, com.crazyspace_edu.api.domain.QGeneratedAiContent>createList("contents", com.crazyspace_edu.api.domain.GeneratedAiContent.class, com.crazyspace_edu.api.domain.QGeneratedAiContent.class, PathInits.DIRECT2);

    public final StringPath email = createString("email");

    public final BooleanPath enabled = createBoolean("enabled");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final StringPath password = createString("password");

    public final StringPath phone = createString("phone");

    public final DateTimePath<java.time.LocalDateTime> reg_dt = createDateTime("reg_dt", java.time.LocalDateTime.class);

    public final EnumPath<AgreeYN> userLocBaseSvcAgmtYN = createEnum("userLocBaseSvcAgmtYN", AgreeYN.class);

    public final EnumPath<AgreeYN> userMktInfoRecvAgmtYN = createEnum("userMktInfoRecvAgmtYN", AgreeYN.class);

    public final EnumPath<AgreeYN> userPsInfoProcAgmtYN = createEnum("userPsInfoProcAgmtYN", AgreeYN.class);

    public final EnumPath<AgreeYN> userPushYN = createEnum("userPushYN", AgreeYN.class);

    public final EnumPath<UserStatus> userStatus = createEnum("userStatus", UserStatus.class);

    public final EnumPath<AgreeYN> userSvcUsePcyAgmtYN = createEnum("userSvcUsePcyAgmtYN", AgreeYN.class);

    public QUser(String variable) {
        super(User.class, forVariable(variable));
    }

    public QUser(Path<? extends User> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUser(PathMetadata metadata) {
        super(User.class, metadata);
    }

}

