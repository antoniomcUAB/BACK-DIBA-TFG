package es.in2.dsdibaapi.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QGravetat is a Querydsl query type for Gravetat
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QGravetat extends EntityPathBase<Gravetat> {

    private static final long serialVersionUID = 369201487L;

    public static final QGravetat gravetat = new QGravetat("gravetat");

    public final StringPath descripcio = createString("descripcio");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> value = createNumber("value", Integer.class);

    public QGravetat(String variable) {
        super(Gravetat.class, forVariable(variable));
    }

    public QGravetat(Path<? extends Gravetat> path) {
        super(path.getType(), path.getMetadata());
    }

    public QGravetat(PathMetadata metadata) {
        super(Gravetat.class, metadata);
    }

}

