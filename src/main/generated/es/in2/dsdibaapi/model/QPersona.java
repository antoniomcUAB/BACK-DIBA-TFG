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

    public static final QPersona persona1 = new QPersona("persona1");

    public final StringPath COGNOM1 = createString("COGNOM1");

    public final StringPath COGNOM2 = createString("COGNOM2");

    public final QExReferencia exReferencia;

    public final NumberPath<Long> ID = createNumber("ID", Long.class);

    public final StringPath NOM = createString("NOM");

    public final QPersona persona;

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
        this.exReferencia = inits.isInitialized("exReferencia") ? new QExReferencia(forProperty("exReferencia")) : null;
        this.persona = inits.isInitialized("persona") ? new QPersona(forProperty("persona"), inits.get("persona")) : null;
    }

}

