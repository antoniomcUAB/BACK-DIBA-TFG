package es.in2.dsdibaapi.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QExReferencia is a Querydsl query type for ExReferencia
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QExReferencia extends EntityPathBase<ExReferencia> {

    private static final long serialVersionUID = -586125486L;

    public static final QExReferencia exReferencia = new QExReferencia("exReferencia");

    public final StringPath DESCRIPCIO = createString("DESCRIPCIO");

    public final NumberPath<Long> ID = createNumber("ID", Long.class);

    public QExReferencia(String variable) {
        super(ExReferencia.class, forVariable(variable));
    }

    public QExReferencia(Path<? extends ExReferencia> path) {
        super(path.getType(), path.getMetadata());
    }

    public QExReferencia(PathMetadata metadata) {
        super(ExReferencia.class, metadata);
    }

}

