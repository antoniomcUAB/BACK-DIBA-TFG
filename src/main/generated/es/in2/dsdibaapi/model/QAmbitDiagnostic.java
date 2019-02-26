package es.in2.dsdibaapi.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QAmbitDiagnostic is a Querydsl query type for AmbitDiagnostic
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QAmbitDiagnostic extends EntityPathBase<AmbitDiagnostic> {

    private static final long serialVersionUID = -662575749L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QAmbitDiagnostic ambitDiagnostic = new QAmbitDiagnostic("ambitDiagnostic");

    public final QAmbit ambit;

    public final QDiagnostic diagnostic;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath observacions = createString("observacions");

    public QAmbitDiagnostic(String variable) {
        this(AmbitDiagnostic.class, forVariable(variable), INITS);
    }

    public QAmbitDiagnostic(Path<? extends AmbitDiagnostic> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QAmbitDiagnostic(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QAmbitDiagnostic(PathMetadata metadata, PathInits inits) {
        this(AmbitDiagnostic.class, metadata, inits);
    }

    public QAmbitDiagnostic(Class<? extends AmbitDiagnostic> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.ambit = inits.isInitialized("ambit") ? new QAmbit(forProperty("ambit")) : null;
        this.diagnostic = inits.isInitialized("diagnostic") ? new QDiagnostic(forProperty("diagnostic"), inits.get("diagnostic")) : null;
    }

}

