package es.in2.dsdibaapi.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QContextualitzacio is a Querydsl query type for Contextualitzacio
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QContextualitzacio extends EntityPathBase<Contextualitzacio> {

    private static final long serialVersionUID = -1675443623L;

    public static final QContextualitzacio contextualitzacio = new QContextualitzacio("contextualitzacio");

    public final StringPath AMBIT = createString("AMBIT");

    public final StringPath CODI = createString("CODI");

    public final StringPath DESCRIPCIO = createString("DESCRIPCIO");

    public final NumberPath<Long> ID = createNumber("ID", Long.class);

    public QContextualitzacio(String variable) {
        super(Contextualitzacio.class, forVariable(variable));
    }

    public QContextualitzacio(Path<? extends Contextualitzacio> path) {
        super(path.getType(), path.getMetadata());
    }

    public QContextualitzacio(PathMetadata metadata) {
        super(Contextualitzacio.class, metadata);
    }

}

