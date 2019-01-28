package es.in2.dsdibaapi.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QFrequencia is a Querydsl query type for Frequencia
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QFrequencia extends EntityPathBase<Frequencia> {

    private static final long serialVersionUID = 1495167778L;

    public static final QFrequencia frequencia = new QFrequencia("frequencia");

    public final StringPath DESCRIPCIO = createString("DESCRIPCIO");

    public final NumberPath<Long> ID = createNumber("ID", Long.class);

    public final NumberPath<Integer> value = createNumber("value", Integer.class);

    public QFrequencia(String variable) {
        super(Frequencia.class, forVariable(variable));
    }

    public QFrequencia(Path<? extends Frequencia> path) {
        super(path.getType(), path.getMetadata());
    }

    public QFrequencia(PathMetadata metadata) {
        super(Frequencia.class, metadata);
    }

}

