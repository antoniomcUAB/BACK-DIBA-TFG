package es.in2.dsdibaapi.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCriteri is a Querydsl query type for Criteri
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QCriteri extends EntityPathBase<Criteri> {

    private static final long serialVersionUID = 71454165L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCriteri criteri = new QCriteri("criteri");

    public final StringPath evidencia = createString("evidencia");

    public final QFrequencia frequencia;

    public final QFrequenciaGravetat frequenciaGravetat;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QRisc risc;

    public QCriteri(String variable) {
        this(Criteri.class, forVariable(variable), INITS);
    }

    public QCriteri(Path<? extends Criteri> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCriteri(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCriteri(PathMetadata metadata, PathInits inits) {
        this(Criteri.class, metadata, inits);
    }

    public QCriteri(Class<? extends Criteri> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.frequencia = inits.isInitialized("frequencia") ? new QFrequencia(forProperty("frequencia")) : null;
        this.frequenciaGravetat = inits.isInitialized("frequenciaGravetat") ? new QFrequenciaGravetat(forProperty("frequenciaGravetat"), inits.get("frequenciaGravetat")) : null;
        this.risc = inits.isInitialized("risc") ? new QRisc(forProperty("risc")) : null;
    }

}

