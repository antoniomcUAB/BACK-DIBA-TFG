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

    public final QExpedient expedient;

    public final QGravetat gravetat;

    public final NumberPath<Long> ID = createNumber("ID", Long.class);

    public final QModalitatDiagnostic modalitatDiagnostic;

    public final QQuestio questio;

    public final QRisc risc;

    public final QTipusPersona tipusPersona;

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
        this.expedient = inits.isInitialized("expedient") ? new QExpedient(forProperty("expedient"), inits.get("expedient")) : null;
        this.gravetat = inits.isInitialized("gravetat") ? new QGravetat(forProperty("gravetat"), inits.get("gravetat")) : null;
        this.modalitatDiagnostic = inits.isInitialized("modalitatDiagnostic") ? new QModalitatDiagnostic(forProperty("modalitatDiagnostic"), inits.get("modalitatDiagnostic")) : null;
        this.questio = inits.isInitialized("questio") ? new QQuestio(forProperty("questio"), inits.get("questio")) : null;
        this.risc = inits.isInitialized("risc") ? new QRisc(forProperty("risc"), inits.get("risc")) : null;
        this.tipusPersona = inits.isInitialized("tipusPersona") ? new QTipusPersona(forProperty("tipusPersona"), inits.get("tipusPersona")) : null;
    }

}

