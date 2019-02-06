package es.in2.dsdibaapi.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QSituacioSocial is a Querydsl query type for SituacioSocial
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QSituacioSocial extends EntityPathBase<SituacioSocial> {

    private static final long serialVersionUID = -575142663L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSituacioSocial situacioSocial = new QSituacioSocial("situacioSocial");

    public final NumberPath<Double> altRisc = createNumber("altRisc", Double.class);

    public final StringPath definicio = createString("definicio");

    public final QEntorn entorn;

    public final ListPath<FrequenciaGravetat, QFrequenciaGravetat> frequenciaGravetat = this.<FrequenciaGravetat, QFrequenciaGravetat>createList("frequenciaGravetat", FrequenciaGravetat.class, QFrequenciaGravetat.class, PathInits.DIRECT2);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Double> risc = createNumber("risc", Double.class);

    public final StringPath social = createString("social");

    public final QVersioModel versioModel;

    public final NumberPath<Double> vulnerabilitat = createNumber("vulnerabilitat", Double.class);

    public QSituacioSocial(String variable) {
        this(SituacioSocial.class, forVariable(variable), INITS);
    }

    public QSituacioSocial(Path<? extends SituacioSocial> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QSituacioSocial(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QSituacioSocial(PathMetadata metadata, PathInits inits) {
        this(SituacioSocial.class, metadata, inits);
    }

    public QSituacioSocial(Class<? extends SituacioSocial> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.entorn = inits.isInitialized("entorn") ? new QEntorn(forProperty("entorn"), inits.get("entorn")) : null;
        this.versioModel = inits.isInitialized("versioModel") ? new QVersioModel(forProperty("versioModel")) : null;
    }

}

