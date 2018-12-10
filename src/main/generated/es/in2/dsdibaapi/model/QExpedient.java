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

    public final StringPath DATA = createString("DATA");

    public final StringPath EXPEDIENT = createString("EXPEDIENT");

    public final QExpedient expedient;

    public final NumberPath<Long> ID = createNumber("ID", Long.class);

    public final StringPath NOM = createString("NOM");

    public final StringPath OBSERVACIONS = createString("OBSERVACIONS");

    public final StringPath PROFESSIONAL = createString("PROFESSIONAL");

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
        this.expedient = inits.isInitialized("expedient") ? new QExpedient(forProperty("expedient"), inits.get("expedient")) : null;
    }

}

