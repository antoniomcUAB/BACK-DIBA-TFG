package es.in2.dsdibaapi.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QModel is a Querydsl query type for Model
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QModel extends EntityPathBase<Criteri> {

    private static final long serialVersionUID = 2029324412L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QModel model = new QModel("model");

    public final QAmbit ambit;

    public final StringPath evidenciaFrequencia = createString("evidenciaFrequencia");

    public final StringPath evidenciaGravetat = createString("evidenciaGravetat");

    public final QFrequencia frequencia;

    public final QGravetat gravetat;

    public final NumberPath<Long> ID = createNumber("ID", Long.class);

    public final QRisc risc;

    public QModel(String variable) {
        this(Criteri.class, forVariable(variable), INITS);
    }

    public QModel(Path<? extends Criteri> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QModel(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QModel(PathMetadata metadata, PathInits inits) {
        this(Criteri.class, metadata, inits);
    }

    public QModel(Class<? extends Criteri> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.ambit = inits.isInitialized("ambit") ? new QAmbit(forProperty("ambit"), inits.get("ambit")) : null;
        this.frequencia = inits.isInitialized("frequencia") ? new QFrequencia(forProperty("frequencia"), inits.get("frequencia")) : null;
        this.gravetat = inits.isInitialized("gravetat") ? new QGravetat(forProperty("gravetat"), inits.get("gravetat")) : null;
        this.risc = inits.isInitialized("risc") ? new QRisc(forProperty("risc"), inits.get("risc")) : null;
    }

}

