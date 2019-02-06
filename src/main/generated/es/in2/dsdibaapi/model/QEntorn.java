package es.in2.dsdibaapi.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QEntorn is a Querydsl query type for Entorn
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QEntorn extends EntityPathBase<Entorn> {

    private static final long serialVersionUID = -1744922835L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QEntorn entorn = new QEntorn("entorn");

    public final QAmbit ambit;

    public final StringPath descripcio = createString("descripcio");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ListPath<SituacioSocial, QSituacioSocial> situacioSocial = this.<SituacioSocial, QSituacioSocial>createList("situacioSocial", SituacioSocial.class, QSituacioSocial.class, PathInits.DIRECT2);

    public QEntorn(String variable) {
        this(Entorn.class, forVariable(variable), INITS);
    }

    public QEntorn(Path<? extends Entorn> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QEntorn(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QEntorn(PathMetadata metadata, PathInits inits) {
        this(Entorn.class, metadata, inits);
    }

    public QEntorn(Class<? extends Entorn> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.ambit = inits.isInitialized("ambit") ? new QAmbit(forProperty("ambit")) : null;
    }

}

