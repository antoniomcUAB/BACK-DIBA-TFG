package es.in2.dsdibaapi.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QContextDiagnostic is a Querydsl query type for ContextDiagnostic
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QContextDiagnostic extends EntityPathBase<ContextDiagnostic> {

    private static final long serialVersionUID = -1863415927L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QContextDiagnostic contextDiagnostic = new QContextDiagnostic("contextDiagnostic");

    public final StringPath CODI_CONTEXT = createString("CODI_CONTEXT");

    public final NumberPath<Long> ID = createNumber("ID", Long.class);

    public final StringPath MEMBRE_UNIC = createString("MEMBRE_UNIC");

    public final StringPath MES_UC = createString("MES_UC");

    public final QPersona persona;

    public final QQuestio questio;

    public QContextDiagnostic(String variable) {
        this(ContextDiagnostic.class, forVariable(variable), INITS);
    }

    public QContextDiagnostic(Path<? extends ContextDiagnostic> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QContextDiagnostic(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QContextDiagnostic(PathMetadata metadata, PathInits inits) {
        this(ContextDiagnostic.class, metadata, inits);
    }

    public QContextDiagnostic(Class<? extends ContextDiagnostic> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.persona = inits.isInitialized("persona") ? new QPersona(forProperty("persona"), inits.get("persona")) : null;
        this.questio = inits.isInitialized("questio") ? new QQuestio(forProperty("questio"), inits.get("questio")) : null;
    }

}

