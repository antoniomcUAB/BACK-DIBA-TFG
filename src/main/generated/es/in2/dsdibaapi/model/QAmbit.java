package es.in2.dsdibaapi.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QAmbit is a Querydsl query type for Ambit
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QAmbit extends EntityPathBase<Ambit> {

    private static final long serialVersionUID = 2018180788L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QAmbit ambit1 = new QAmbit("ambit1");

    public final StringPath AMBIT = createString("AMBIT");

    public final QAmbit ambit;

    public final StringPath DEFINICIO = createString("DEFINICIO");

    public final NumberPath<Long> ID = createNumber("ID", Long.class);

    public final StringPath SOCIAL = createString("SOCIAL");

    public QAmbit(String variable) {
        this(Ambit.class, forVariable(variable), INITS);
    }

    public QAmbit(Path<? extends Ambit> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QAmbit(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QAmbit(PathMetadata metadata, PathInits inits) {
        this(Ambit.class, metadata, inits);
    }

    public QAmbit(Class<? extends Ambit> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.ambit = inits.isInitialized("ambit") ? new QAmbit(forProperty("ambit"), inits.get("ambit")) : null;
    }

}

