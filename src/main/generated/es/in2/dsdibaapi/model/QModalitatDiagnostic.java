package es.in2.dsdibaapi.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QModalitatDiagnostic is a Querydsl query type for ModalitatDiagnostic
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QModalitatDiagnostic extends EntityPathBase<ModalitatDiagnostic> {

    private static final long serialVersionUID = 928845029L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QModalitatDiagnostic modalitatDiagnostic1 = new QModalitatDiagnostic("modalitatDiagnostic1");

    public final StringPath CODI = createString("CODI");

    public final StringPath DESCRIPCIO = createString("DESCRIPCIO");

    public final NumberPath<Long> ID = createNumber("ID", Long.class);

    public final QModalitatDiagnostic modalitatDiagnostic;

    public QModalitatDiagnostic(String variable) {
        this(ModalitatDiagnostic.class, forVariable(variable), INITS);
    }

    public QModalitatDiagnostic(Path<? extends ModalitatDiagnostic> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QModalitatDiagnostic(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QModalitatDiagnostic(PathMetadata metadata, PathInits inits) {
        this(ModalitatDiagnostic.class, metadata, inits);
    }

    public QModalitatDiagnostic(Class<? extends ModalitatDiagnostic> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.modalitatDiagnostic = inits.isInitialized("modalitatDiagnostic") ? new QModalitatDiagnostic(forProperty("modalitatDiagnostic"), inits.get("modalitatDiagnostic")) : null;
    }

}

