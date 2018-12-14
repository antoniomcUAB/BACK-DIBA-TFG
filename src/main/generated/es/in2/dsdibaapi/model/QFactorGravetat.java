package es.in2.dsdibaapi.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QFactorGravetat is a Querydsl query type for FactorGravetat
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QFactorGravetat extends EntityPathBase<FactorGravetat> {

    private static final long serialVersionUID = -792403458L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QFactorGravetat factorGravetat = new QFactorGravetat("factorGravetat");

    public final QEntorn entorn;

    public final QFactor factor;

    public final QGravetat gravetat;

    public final NumberPath<Long> ID = createNumber("ID", Long.class);

    public QFactorGravetat(String variable) {
        this(FactorGravetat.class, forVariable(variable), INITS);
    }

    public QFactorGravetat(Path<? extends FactorGravetat> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QFactorGravetat(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QFactorGravetat(PathMetadata metadata, PathInits inits) {
        this(FactorGravetat.class, metadata, inits);
    }

    public QFactorGravetat(Class<? extends FactorGravetat> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.entorn = inits.isInitialized("entorn") ? new QEntorn(forProperty("entorn"), inits.get("entorn")) : null;
        this.factor = inits.isInitialized("factor") ? new QFactor(forProperty("factor"), inits.get("factor")) : null;
        this.gravetat = inits.isInitialized("gravetat") ? new QGravetat(forProperty("gravetat"), inits.get("gravetat")) : null;
    }

}

