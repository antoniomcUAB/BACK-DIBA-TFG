package es.in2.dsdibaapi.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QQuestio is a Querydsl query type for Questio
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QQuestio extends EntityPathBase<Questio> {

    private static final long serialVersionUID = -306218469L;

    public static final QQuestio questio = new QQuestio("questio");

    public final StringPath CODI = createString("CODI");

    public final StringPath DESCRIPCIO = createString("DESCRIPCIO");

    public final NumberPath<Long> ID = createNumber("ID", Long.class);

    public QQuestio(String variable) {
        super(Questio.class, forVariable(variable));
    }

    public QQuestio(Path<? extends Questio> path) {
        super(path.getType(), path.getMetadata());
    }

    public QQuestio(PathMetadata metadata) {
        super(Questio.class, metadata);
    }

}

