package es.in2.dsdibaapi.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QQuestio is a Querydsl query type for Questio
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QQuestio extends EntityPathBase<Questio> {

    private static final long serialVersionUID = -306218469L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QQuestio questio1 = new QQuestio("questio1");

    public final StringPath CODI = createString("CODI");

    public final StringPath DESCRIPCIO = createString("DESCRIPCIO");

    public final NumberPath<Long> ID = createNumber("ID", Long.class);

    public final QQuestio questio;

    public QQuestio(String variable) {
        this(Questio.class, forVariable(variable), INITS);
    }

    public QQuestio(Path<? extends Questio> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QQuestio(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QQuestio(PathMetadata metadata, PathInits inits) {
        this(Questio.class, metadata, inits);
    }

    public QQuestio(Class<? extends Questio> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.questio = inits.isInitialized("questio") ? new QQuestio(forProperty("questio"), inits.get("questio")) : null;
    }

}

