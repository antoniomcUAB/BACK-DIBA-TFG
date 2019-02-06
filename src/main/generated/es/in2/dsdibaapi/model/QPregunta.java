package es.in2.dsdibaapi.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPregunta is a Querydsl query type for Pregunta
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QPregunta extends EntityPathBase<Pregunta> {

    private static final long serialVersionUID = -1024239433L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPregunta pregunta = new QPregunta("pregunta");

    public final QDiagnostic diagnostic;

    public final QEntorn entorn;

    public final QRisc factor;

    public final SetPath<FactorEconomic, QFactorEconomic> factorEconomic = this.<FactorEconomic, QFactorEconomic>createSet("factorEconomic", FactorEconomic.class, QFactorEconomic.class, PathInits.DIRECT2);

    public final QFrequencia frequencia;

    public final QGravetat gravetat;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QPersona persona;

    public final QRisc risc;

    public final QSituacioSocial situacioSocial;

    public final BooleanPath unitatFamiliar = createBoolean("unitatFamiliar");

    public QPregunta(String variable) {
        this(Pregunta.class, forVariable(variable), INITS);
    }

    public QPregunta(Path<? extends Pregunta> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPregunta(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPregunta(PathMetadata metadata, PathInits inits) {
        this(Pregunta.class, metadata, inits);
    }

    public QPregunta(Class<? extends Pregunta> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.diagnostic = inits.isInitialized("diagnostic") ? new QDiagnostic(forProperty("diagnostic"), inits.get("diagnostic")) : null;
        this.entorn = inits.isInitialized("entorn") ? new QEntorn(forProperty("entorn"), inits.get("entorn")) : null;
        this.factor = inits.isInitialized("factor") ? new QRisc(forProperty("factor")) : null;
        this.frequencia = inits.isInitialized("frequencia") ? new QFrequencia(forProperty("frequencia")) : null;
        this.gravetat = inits.isInitialized("gravetat") ? new QGravetat(forProperty("gravetat")) : null;
        this.persona = inits.isInitialized("persona") ? new QPersona(forProperty("persona"), inits.get("persona")) : null;
        this.risc = inits.isInitialized("risc") ? new QRisc(forProperty("risc")) : null;
        this.situacioSocial = inits.isInitialized("situacioSocial") ? new QSituacioSocial(forProperty("situacioSocial"), inits.get("situacioSocial")) : null;
    }

}

