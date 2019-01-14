package es.in2.dsdibaapi.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QContextDiagnostic is a Querydsl query type for ContextDiagnostic
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QContextDiagnostic extends EntityPathBase<ContextDiagnostic> {

    private static final long serialVersionUID = -1863415927L;

    public static final QContextDiagnostic contextDiagnostic = new QContextDiagnostic("contextDiagnostic");

    public final StringPath CODI_CONTEXT = createString("CODI_CONTEXT");

    public final NumberPath<Long> ID = createNumber("ID", Long.class);

    public final StringPath MEMBRE_UNIC = createString("MEMBRE_UNIC");

    public final StringPath MES_UC = createString("MES_UC");

    public QContextDiagnostic(String variable) {
        super(ContextDiagnostic.class, forVariable(variable));
    }

    public QContextDiagnostic(Path<? extends ContextDiagnostic> path) {
        super(path.getType(), path.getMetadata());
    }

    public QContextDiagnostic(PathMetadata metadata) {
        super(ContextDiagnostic.class, metadata);
    }

}

