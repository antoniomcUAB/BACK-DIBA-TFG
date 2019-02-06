package es.in2.dsdibaapi.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QMunicipi is a Querydsl query type for Municipi
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QMunicipi extends EntityPathBase<Municipi> {

    private static final long serialVersionUID = 963776143L;

    public static final QMunicipi municipi = new QMunicipi("municipi");

    public final StringPath descripcio = createString("descripcio");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public QMunicipi(String variable) {
        super(Municipi.class, forVariable(variable));
    }

    public QMunicipi(Path<? extends Municipi> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMunicipi(PathMetadata metadata) {
        super(Municipi.class, metadata);
    }

}

