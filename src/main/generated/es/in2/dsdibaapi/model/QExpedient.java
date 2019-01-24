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

    public static final QExpedient expedient1 = new QExpedient("expedient1");

    public final ListPath<Contextualitzacio, QContextualitzacio> contextualitzacio = this.<Contextualitzacio, QContextualitzacio>createList("contextualitzacio", Contextualitzacio.class, QContextualitzacio.class, PathInits.DIRECT2);

    public final DateTimePath<java.util.Date> DATA = createDateTime("DATA", java.util.Date.class);

    public final ListPath<Diagnostic, QDiagnostic> diagnostic = this.<Diagnostic, QDiagnostic>createList("diagnostic", Diagnostic.class, QDiagnostic.class, PathInits.DIRECT2);

    public final NumberPath<Long> diagnosticEconomic = createNumber("diagnosticEconomic", Long.class);

    public final StringPath estat = createString("estat");

    public final StringPath expedient = createString("expedient");

    public final NumberPath<Long> ID = createNumber("ID", Long.class);

    public final StringPath NOM = createString("NOM");

    public final StringPath OBSERVACIONS = createString("OBSERVACIONS");

    public final SetPath<Persona, QPersona> persona = this.<Persona, QPersona>createSet("persona", Persona.class, QPersona.class, PathInits.DIRECT2);

    public final QProfessional professional;

    public final NumberPath<Long> totalFamilia = createNumber("totalFamilia", Long.class);

    public final QValoracio valoracio;

    public final QVersioModel versioModel;

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
        this.professional = inits.isInitialized("professional") ? new QProfessional(forProperty("professional"), inits.get("professional")) : null;
        this.valoracio = inits.isInitialized("valoracio") ? new QValoracio(forProperty("valoracio")) : null;
        this.versioModel = inits.isInitialized("versioModel") ? new QVersioModel(forProperty("versioModel")) : null;
    }

}

