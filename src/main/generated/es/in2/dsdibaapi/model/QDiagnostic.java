package es.in2.dsdibaapi.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QDiagnostic is a Querydsl query type for Diagnostic
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QDiagnostic extends EntityPathBase<Diagnostic> {

    private static final long serialVersionUID = 2118003252L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QDiagnostic diagnostic = new QDiagnostic("diagnostic");

    public final DateTimePath<java.util.Date> data = createDateTime("data", java.util.Date.class);

    public final QEstat estat;

    public final QExpedient expedient;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath observacions = createString("observacions");

    public final QProfessional professional;

    public final QValoracio valoracio;

    public final QVersioModel versioModel;

    public QDiagnostic(String variable) {
        this(Diagnostic.class, forVariable(variable), INITS);
    }

    public QDiagnostic(Path<? extends Diagnostic> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QDiagnostic(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QDiagnostic(PathMetadata metadata, PathInits inits) {
        this(Diagnostic.class, metadata, inits);
    }

    public QDiagnostic(Class<? extends Diagnostic> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.estat = inits.isInitialized("estat") ? new QEstat(forProperty("estat")) : null;
        this.expedient = inits.isInitialized("expedient") ? new QExpedient(forProperty("expedient"), inits.get("expedient")) : null;
        this.professional = inits.isInitialized("professional") ? new QProfessional(forProperty("professional"), inits.get("professional")) : null;
        this.valoracio = inits.isInitialized("valoracio") ? new QValoracio(forProperty("valoracio")) : null;
        this.versioModel = inits.isInitialized("versioModel") ? new QVersioModel(forProperty("versioModel")) : null;
    }

}

