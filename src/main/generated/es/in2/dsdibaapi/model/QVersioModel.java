package es.in2.dsdibaapi.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QVersioModel is a Querydsl query type for VersioModel
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QVersioModel extends EntityPathBase<VersioModel> {

    private static final long serialVersionUID = -1661573754L;

    public static final QVersioModel versioModel = new QVersioModel("versioModel");

    public final DateTimePath<java.util.Date> data = createDateTime("data", java.util.Date.class);

    public final NumberPath<Long> ID = createNumber("ID", Long.class);

    public final StringPath versio = createString("versio");

    public QVersioModel(String variable) {
        super(VersioModel.class, forVariable(variable));
    }

    public QVersioModel(Path<? extends VersioModel> path) {
        super(path.getType(), path.getMetadata());
    }

    public QVersioModel(PathMetadata metadata) {
        super(VersioModel.class, metadata);
    }

}

