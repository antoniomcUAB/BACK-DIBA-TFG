package es.in2.dsdibaapi.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QPersona is a Querydsl query type for Persona
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QPersona extends EntityPathBase<Persona> {

    private static final long serialVersionUID = -1639787457L;

    public static final QPersona persona = new QPersona("persona");

    public final StringPath COGNOM1 = createString("COGNOM1");

    public final StringPath COGNOM2 = createString("COGNOM2");

    public final NumberPath<Long> ID = createNumber("ID", Long.class);

    public final StringPath NOM = createString("NOM");

    public QPersona(String variable) {
        super(Persona.class, forVariable(variable));
    }

    public QPersona(Path<? extends Persona> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPersona(PathMetadata metadata) {
        super(Persona.class, metadata);
    }

}

