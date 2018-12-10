package es.in2.dsdibaapi.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRisc is a Querydsl query type for Risc
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QRisc extends EntityPathBase<Risc> {

    private static final long serialVersionUID = 1866721044L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRisc risc1 = new QRisc("risc1");

    public final StringPath DESCRIPCIO = createString("DESCRIPCIO");

    public final NumberPath<Long> ID = createNumber("ID", Long.class);

    public final QRisc risc;

    public QRisc(String variable) {
        this(Risc.class, forVariable(variable), INITS);
    }

    public QRisc(Path<? extends Risc> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QRisc(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QRisc(PathMetadata metadata, PathInits inits) {
        this(Risc.class, metadata, inits);
    }

    public QRisc(Class<? extends Risc> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.risc = inits.isInitialized("risc") ? new QRisc(forProperty("risc"), inits.get("risc")) : null;
    }

}

