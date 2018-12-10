package es.in2.dsdibaapi.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QFactorContext is a Querydsl query type for FactorContext
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QFactorContext extends EntityPathBase<FactorContext> {

    private static final long serialVersionUID = 229808051L;

    public static final QFactorContext factorContext = new QFactorContext("factorContext");

    public final StringPath DESCRIPCIO = createString("DESCRIPCIO");

    public final NumberPath<Long> ID = createNumber("ID", Long.class);

    public QFactorContext(String variable) {
        super(FactorContext.class, forVariable(variable));
    }

    public QFactorContext(Path<? extends FactorContext> path) {
        super(path.getType(), path.getMetadata());
    }

    public QFactorContext(PathMetadata metadata) {
        super(FactorContext.class, metadata);
    }

}

