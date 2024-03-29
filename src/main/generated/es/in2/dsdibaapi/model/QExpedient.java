package es.in2.dsdibaapi.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QExpedient is a Querydsl query type for Expedient
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QExpedient extends EntityPathBase<Expedient> {

    private static final long serialVersionUID = -313076431L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QExpedient expedient = new QExpedient("expedient");

    public final StringPath codi = createString("codi");

    public final DateTimePath<java.util.Date> dataCreacio = createDateTime("dataCreacio", java.util.Date.class);

    public final DateTimePath<java.util.Date> dataValidacio = createDateTime("dataValidacio", java.util.Date.class);

    public final ListPath<Diagnostic, QDiagnostic> diagnostic = this.<Diagnostic, QDiagnostic>createList("diagnostic", Diagnostic.class, QDiagnostic.class, PathInits.DIRECT2);

    public final QEstat estat;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath observacions = createString("observacions");

    public final SetPath<Persona, QPersona> persona = this.<Persona, QPersona>createSet("persona", Persona.class, QPersona.class, PathInits.DIRECT2);

    public final QProfessional professional;

    public final NumberPath<Long> totalFamilia = createNumber("totalFamilia", Long.class);

    public QExpedient(String variable) {
        this(Expedient.class, forVariable(variable), INITS);
    }

    public QExpedient(Path<? extends Expedient> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QExpedient(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QExpedient(PathMetadata metadata, PathInits inits) {
        this(Expedient.class, metadata, inits);
    }

    public QExpedient(Class<? extends Expedient> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.estat = inits.isInitialized("estat") ? new QEstat(forProperty("estat")) : null;
        this.professional = inits.isInitialized("professional") ? new QProfessional(forProperty("professional"), inits.get("professional")) : null;
    }

}

