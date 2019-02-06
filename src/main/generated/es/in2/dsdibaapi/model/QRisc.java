package es.in2.dsdibaapi.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QRisc is a Querydsl query type for Risc
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QRisc extends EntityPathBase<Risc> {

    private static final long serialVersionUID = 1866721044L;

    public static final QRisc risc = new QRisc("risc");

    public final StringPath descripcio = createString("descripcio");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> value = createNumber("value", Integer.class);

    public QRisc(String variable) {
        super(Risc.class, forVariable(variable));
    }

    public QRisc(Path<? extends Risc> path) {
        super(path.getType(), path.getMetadata());
    }

    public QRisc(PathMetadata metadata) {
        super(Risc.class, metadata);
    }

}

