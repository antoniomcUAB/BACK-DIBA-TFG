package es.in2.dsdibaapi.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QEconomia is a Querydsl query type for Economia
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QEconomia extends EntityPathBase<Economia> {

    private static final long serialVersionUID = 1144606144L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QEconomia economia = new QEconomia("economia");

    public final QFactorEconomic factor;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QPregunta pregunta;

    public QEconomia(String variable) {
        this(Economia.class, forVariable(variable), INITS);
    }

    public QEconomia(Path<? extends Economia> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QEconomia(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QEconomia(PathMetadata metadata, PathInits inits) {
        this(Economia.class, metadata, inits);
    }

    public QEconomia(Class<? extends Economia> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.factor = inits.isInitialized("factor") ? new QFactorEconomic(forProperty("factor"), inits.get("factor")) : null;
        this.pregunta = inits.isInitialized("pregunta") ? new QPregunta(forProperty("pregunta"), inits.get("pregunta")) : null;
    }

}

