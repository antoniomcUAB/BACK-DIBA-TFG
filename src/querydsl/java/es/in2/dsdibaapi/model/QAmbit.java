package es.in2.dsdibaapi.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QAmbit is a Querydsl query type for Ambit
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QAmbit extends EntityPathBase<Ambit> {

    private static final long serialVersionUID = 2018180788L;

    public static final QAmbit ambit = new QAmbit("ambit");

    public final StringPath AMBIT = createString("AMBIT");

    public final StringPath DEFINICIO = createString("DEFINICIO");

    public final NumberPath<Long> ID = createNumber("ID", Long.class);

    public final StringPath SOCIAL = createString("SOCIAL");

    public QAmbit(String variable) {
        super(Ambit.class, forVariable(variable));
    }

    public QAmbit(Path<? extends Ambit> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAmbit(PathMetadata metadata) {
        super(Ambit.class, metadata);
    }

}

