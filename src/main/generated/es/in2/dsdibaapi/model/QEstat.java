package es.in2.dsdibaapi.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QEstat is a Querydsl query type for Estat
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QEstat extends EntityPathBase<Estat> {

    private static final long serialVersionUID = 2022070668L;

    public static final QEstat estat = new QEstat("estat");

    public final StringPath descripcio = createString("descripcio");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public QEstat(String variable) {
        super(Estat.class, forVariable(variable));
    }

    public QEstat(Path<? extends Estat> path) {
        super(path.getType(), path.getMetadata());
    }

    public QEstat(PathMetadata metadata) {
        super(Estat.class, metadata);
    }

}

