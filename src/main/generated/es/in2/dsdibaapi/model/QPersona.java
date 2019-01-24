package es.in2.dsdibaapi.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPersona is a Querydsl query type for Persona
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QPersona extends EntityPathBase<Persona> {

    private static final long serialVersionUID = -1639787457L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPersona persona = new QPersona("persona");

    public final DateTimePath<java.util.Date> dataAlta = createDateTime("dataAlta", java.util.Date.class);

    public final DateTimePath<java.util.Date> dataBaixa = createDateTime("dataBaixa", java.util.Date.class);

    public final DateTimePath<java.util.Date> dataNaixement = createDateTime("dataNaixement", java.util.Date.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath referencia = createBoolean("referencia");

    public final StringPath sexe = createString("sexe");

    public final QTipusPersona tipusPersona;

    public QPersona(String variable) {
        this(Persona.class, forVariable(variable), INITS);
    }

    public QPersona(Path<? extends Persona> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPersona(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPersona(PathMetadata metadata, PathInits inits) {
        this(Persona.class, metadata, inits);
    }

    public QPersona(Class<? extends Persona> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.tipusPersona = inits.isInitialized("tipusPersona") ? new QTipusPersona(forProperty("tipusPersona")) : null;
    }

}

