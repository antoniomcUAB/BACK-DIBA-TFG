package es.in2.dsdibaapi.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QFrequenciaGravetat is a Querydsl query type for FrequenciaGravetat
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QFrequenciaGravetat extends EntityPathBase<FrequenciaGravetat> {

    private static final long serialVersionUID = -733964092L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QFrequenciaGravetat frequenciaGravetat = new QFrequenciaGravetat("frequenciaGravetat");

    public final ListPath<Criteri, QCriteri> criteri = this.<Criteri, QCriteri>createList("criteri", Criteri.class, QCriteri.class, PathInits.DIRECT2);

    public final StringPath EVIDENCIA = createString("EVIDENCIA");

    public final QGravetat gravetat;

    public final NumberPath<Long> ID = createNumber("ID", Long.class);

    public final QSituacioSocial situacioSocial;

    public QFrequenciaGravetat(String variable) {
        this(FrequenciaGravetat.class, forVariable(variable), INITS);
    }

    public QFrequenciaGravetat(Path<? extends FrequenciaGravetat> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QFrequenciaGravetat(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QFrequenciaGravetat(PathMetadata metadata, PathInits inits) {
        this(FrequenciaGravetat.class, metadata, inits);
    }

    public QFrequenciaGravetat(Class<? extends FrequenciaGravetat> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.gravetat = inits.isInitialized("gravetat") ? new QGravetat(forProperty("gravetat"), inits.get("gravetat")) : null;
        this.situacioSocial = inits.isInitialized("situacioSocial") ? new QSituacioSocial(forProperty("situacioSocial"), inits.get("situacioSocial")) : null;
    }

}

