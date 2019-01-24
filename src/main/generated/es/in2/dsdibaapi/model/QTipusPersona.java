package es.in2.dsdibaapi.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QTipusPersona is a Querydsl query type for TipusPersona
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QTipusPersona extends EntityPathBase<TipusPersona> {

    private static final long serialVersionUID = 1443620928L;

    public static final QTipusPersona tipusPersona = new QTipusPersona("tipusPersona");

    public final StringPath descripcio = createString("descripcio");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public QTipusPersona(String variable) {
        super(TipusPersona.class, forVariable(variable));
    }

    public QTipusPersona(Path<? extends TipusPersona> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTipusPersona(PathMetadata metadata) {
        super(TipusPersona.class, metadata);
    }

}

