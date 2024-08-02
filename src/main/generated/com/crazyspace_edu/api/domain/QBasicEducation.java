package com.crazyspace_edu.api.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QBasicEducation is a Querydsl query type for BasicEducation
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBasicEducation extends EntityPathBase<BasicEducation> {

    private static final long serialVersionUID = -947625285L;

    public static final QBasicEducation basicEducation = new QBasicEducation("basicEducation");

    public final NumberPath<Integer> edu_progress = createNumber("edu_progress", Integer.class);

    public final StringPath edu_text = createString("edu_text");

    public final StringPath edu_title = createString("edu_title");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public QBasicEducation(String variable) {
        super(BasicEducation.class, forVariable(variable));
    }

    public QBasicEducation(Path<? extends BasicEducation> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBasicEducation(PathMetadata metadata) {
        super(BasicEducation.class, metadata);
    }

}

