package es.in2.dsdibaapi.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QDiagnostic is a Querydsl query type for Diagnostic
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QDiagnostic extends EntityPathBase<Diagnostic> {

    private static final long serialVersionUID = 2118003252L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QDiagnostic diagnostic = new QDiagnostic("diagnostic");

    public final QEntorn entorn;

    public final QExpedient expedient;

    public final QRisc factor;

    public final SetPath<FactorEconomic, QFactorEconomic> factorEconomic = this.<FactorEconomic, QFactorEconomic>createSet("factorEconomic", FactorEconomic.class, QFactorEconomic.class, PathInits.DIRECT2);

    public final QFrequencia frequencia;

    public final QGravetat gravetat;

    public final NumberPath<Long> ID = createNumber("ID", Long.class);

    public final QPersona persona;

    public final QRisc risc;

    public final QSituacioSocial situacioSocial;

    public final BooleanPath unitatFamiliar = createBoolean("unitatFamiliar");

    public QDiagnostic(String variable) {
        this(Diagnostic.class, forVariable(variable), INITS);
    }

    public QDiagnostic(Path<? extends Diagnostic> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QDiagnostic(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QDiagnostic(PathMetadata metadata, PathInits inits) {
        this(Diagnostic.class, metadata, inits);
    }

    public QDiagnostic(Class<? extends Diagnostic> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.entorn = inits.isInitialized("entorn") ? new QEntorn(forProperty("entorn"), inits.get("entorn")) : null;
        this.expedient = inits.isInitialized("expedient") ? new QExpedient(forProperty("expedient"), inits.get("expedient")) : null;
        this.factor = inits.isInitialized("factor") ? new QRisc(forProperty("factor")) : null;
        this.frequencia = inits.isInitialized("frequencia") ? new QFrequencia(forProperty("frequencia")) : null;
        this.gravetat = inits.isInitialized("gravetat") ? new QGravetat(forProperty("gravetat")) : null;
        this.persona = inits.isInitialized("persona") ? new QPersona(forProperty("persona"), inits.get("persona")) : null;
        this.risc = inits.isInitialized("risc") ? new QRisc(forProperty("risc")) : null;
        this.situacioSocial = inits.isInitialized("situacioSocial") ? new QSituacioSocial(forProperty("situacioSocial"), inits.get("situacioSocial")) : null;
    }

}

