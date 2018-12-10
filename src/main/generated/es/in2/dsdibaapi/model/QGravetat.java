package es.in2.dsdibaapi.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QGravetat is a Querydsl query type for Gravetat
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QGravetat extends EntityPathBase<Gravetat> {

    private static final long serialVersionUID = 369201487L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QGravetat gravetat1 = new QGravetat("gravetat1");

    public final StringPath DESCRIPCIO = createString("DESCRIPCIO");

    public final QGravetat gravetat;

    public final NumberPath<Long> ID = createNumber("ID", Long.class);

    public QGravetat(String variable) {
        this(Gravetat.class, forVariable(variable), INITS);
    }

    public QGravetat(Path<? extends Gravetat> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QGravetat(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QGravetat(PathMetadata metadata, PathInits inits) {
        this(Gravetat.class, metadata, inits);
    }

    public QGravetat(Class<? extends Gravetat> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.gravetat = inits.isInitialized("gravetat") ? new QGravetat(forProperty("gravetat"), inits.get("gravetat")) : null;
    }

}

