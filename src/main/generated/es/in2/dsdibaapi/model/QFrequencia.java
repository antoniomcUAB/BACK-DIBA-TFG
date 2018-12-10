package es.in2.dsdibaapi.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QFrequencia is a Querydsl query type for Frequencia
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QFrequencia extends EntityPathBase<Frequencia> {

    private static final long serialVersionUID = 1495167778L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QFrequencia frequencia1 = new QFrequencia("frequencia1");

    public final StringPath DESCRIPCIO = createString("DESCRIPCIO");

    public final QFrequencia frequencia;

    public final NumberPath<Long> ID = createNumber("ID", Long.class);

    public QFrequencia(String variable) {
        this(Frequencia.class, forVariable(variable), INITS);
    }

    public QFrequencia(Path<? extends Frequencia> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QFrequencia(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QFrequencia(PathMetadata metadata, PathInits inits) {
        this(Frequencia.class, metadata, inits);
    }

    public QFrequencia(Class<? extends Frequencia> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.frequencia = inits.isInitialized("frequencia") ? new QFrequencia(forProperty("frequencia"), inits.get("frequencia")) : null;
    }

}

