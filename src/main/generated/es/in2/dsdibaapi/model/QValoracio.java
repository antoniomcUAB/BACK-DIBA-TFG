package es.in2.dsdibaapi.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QValoracio is a Querydsl query type for Valoracio
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QValoracio extends EntityPathBase<Valoracio> {

    private static final long serialVersionUID = -1361030305L;

    public static final QValoracio valoracio = new QValoracio("valoracio");

    public final ListPath<Avaluacio, QAvaluacio> avaluacio = this.<Avaluacio, QAvaluacio>createList("avaluacio", Avaluacio.class, QAvaluacio.class, PathInits.DIRECT2);

    public final DateTimePath<java.util.Date> data = createDateTime("data", java.util.Date.class);

    public final NumberPath<Long> factors = createNumber("factors", Long.class);

    public final NumberPath<Long> ID = createNumber("ID", Long.class);

    public final NumberPath<Double> total = createNumber("total", Double.class);

    public QValoracio(String variable) {
        super(Valoracio.class, forVariable(variable));
    }

    public QValoracio(Path<? extends Valoracio> path) {
        super(path.getType(), path.getMetadata());
    }

    public QValoracio(PathMetadata metadata) {
        super(Valoracio.class, metadata);
    }

}

