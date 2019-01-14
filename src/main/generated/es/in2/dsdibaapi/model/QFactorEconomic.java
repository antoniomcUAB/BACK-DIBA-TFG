package es.in2.dsdibaapi.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QFactorEconomic is a Querydsl query type for FactorEconomic
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QFactorEconomic extends EntityPathBase<FactorEconomic> {

    private static final long serialVersionUID = -16998799L;

    public static final QFactorEconomic factorEconomic = new QFactorEconomic("factorEconomic");

    public final StringPath DESCRIPCIO = createString("DESCRIPCIO");

    public final NumberPath<Long> ID = createNumber("ID", Long.class);

    public QFactorEconomic(String variable) {
        super(FactorEconomic.class, forVariable(variable));
    }

    public QFactorEconomic(Path<? extends FactorEconomic> path) {
        super(path.getType(), path.getMetadata());
    }

    public QFactorEconomic(PathMetadata metadata) {
        super(FactorEconomic.class, metadata);
    }

}

