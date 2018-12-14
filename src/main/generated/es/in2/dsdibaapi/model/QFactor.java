package es.in2.dsdibaapi.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QFactor is a Querydsl query type for Factor
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QFactor extends EntityPathBase<Factor> {

    private static final long serialVersionUID = -1728801188L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QFactor factor1 = new QFactor("factor1");

    public final StringPath DESCRIPCIO = createString("DESCRIPCIO");

    public final QFactor factor;

    public final NumberPath<Long> ID = createNumber("ID", Long.class);

    public QFactor(String variable) {
        this(Factor.class, forVariable(variable), INITS);
    }

    public QFactor(Path<? extends Factor> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QFactor(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QFactor(PathMetadata metadata, PathInits inits) {
        this(Factor.class, metadata, inits);
    }

    public QFactor(Class<? extends Factor> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.factor = inits.isInitialized("factor") ? new QFactor(forProperty("factor"), inits.get("factor")) : null;
    }

}

