package es.in2.dsdibaapi.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QTipusPersona is a Querydsl query type for TipusPersona
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QTipusPersona extends EntityPathBase<TipusPersona> {

    private static final long serialVersionUID = 1443620928L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QTipusPersona tipusPersona1 = new QTipusPersona("tipusPersona1");

    public final StringPath DESCRIPCIO = createString("DESCRIPCIO");

    public final NumberPath<Long> ID = createNumber("ID", Long.class);

    public final QTipusPersona tipusPersona;

    public QTipusPersona(String variable) {
        this(TipusPersona.class, forVariable(variable), INITS);
    }

    public QTipusPersona(Path<? extends TipusPersona> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QTipusPersona(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QTipusPersona(PathMetadata metadata, PathInits inits) {
        this(TipusPersona.class, metadata, inits);
    }

    public QTipusPersona(Class<? extends TipusPersona> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.tipusPersona = inits.isInitialized("tipusPersona") ? new QTipusPersona(forProperty("tipusPersona"), inits.get("tipusPersona")) : null;
    }

}

