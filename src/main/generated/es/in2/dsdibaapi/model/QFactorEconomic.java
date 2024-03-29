package es.in2.dsdibaapi.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QFactorEconomic is a Querydsl query type for FactorEconomic
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QFactorEconomic extends EntityPathBase<FactorEconomic> {

    private static final long serialVersionUID = -16998799L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QFactorEconomic factorEconomic = new QFactorEconomic("factorEconomic");

    public final StringPath descripcio = createString("descripcio");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QVersioModel versioModel;

    public QFactorEconomic(String variable) {
        this(FactorEconomic.class, forVariable(variable), INITS);
    }

    public QFactorEconomic(Path<? extends FactorEconomic> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QFactorEconomic(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QFactorEconomic(PathMetadata metadata, PathInits inits) {
        this(FactorEconomic.class, metadata, inits);
    }

    public QFactorEconomic(Class<? extends FactorEconomic> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.versioModel = inits.isInitialized("versioModel") ? new QVersioModel(forProperty("versioModel")) : null;
    }

}

