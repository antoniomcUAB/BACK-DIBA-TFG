package es.in2.dsdibaapi.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QContextualitzacio is a Querydsl query type for Contextualitzacio
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QContextualitzacio extends EntityPathBase<Contextualitzacio> {

    private static final long serialVersionUID = -1675443623L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QContextualitzacio contextualitzacio = new QContextualitzacio("contextualitzacio");

    public final QExpedient expedient;

    public final QFactor factor;

    public final NumberPath<Long> ID = createNumber("ID", Long.class);

    public final BooleanPath membreUnic = createBoolean("membreUnic");

    public final BooleanPath mesUC = createBoolean("mesUC");

    public final QPersona persona;

    public QContextualitzacio(String variable) {
        this(Contextualitzacio.class, forVariable(variable), INITS);
    }

    public QContextualitzacio(Path<? extends Contextualitzacio> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QContextualitzacio(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QContextualitzacio(PathMetadata metadata, PathInits inits) {
        this(Contextualitzacio.class, metadata, inits);
    }

    public QContextualitzacio(Class<? extends Contextualitzacio> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.expedient = inits.isInitialized("expedient") ? new QExpedient(forProperty("expedient"), inits.get("expedient")) : null;
        this.factor = inits.isInitialized("factor") ? new QFactor(forProperty("factor"), inits.get("factor")) : null;
        this.persona = inits.isInitialized("persona") ? new QPersona(forProperty("persona")) : null;
    }

}

