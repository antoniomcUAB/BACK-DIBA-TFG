package es.in2.dsdibaapi.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QAvaluacio is a Querydsl query type for Avaluacio
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QAvaluacio extends EntityPathBase<Avaluacio> {

    private static final long serialVersionUID = -1075636368L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QAvaluacio avaluacio = new QAvaluacio("avaluacio");

    public final QAmbitDiagnostic ambit;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath justificacio = createString("justificacio");

    public final QRisc risc;

    public final QRisc riscProfessional;

    public final QValoracio valoracio;

    public QAvaluacio(String variable) {
        this(Avaluacio.class, forVariable(variable), INITS);
    }

    public QAvaluacio(Path<? extends Avaluacio> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QAvaluacio(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QAvaluacio(PathMetadata metadata, PathInits inits) {
        this(Avaluacio.class, metadata, inits);
    }

    public QAvaluacio(Class<? extends Avaluacio> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.ambit = inits.isInitialized("ambit") ? new QAmbitDiagnostic(forProperty("ambit"), inits.get("ambit")) : null;
        this.risc = inits.isInitialized("risc") ? new QRisc(forProperty("risc")) : null;
        this.riscProfessional = inits.isInitialized("riscProfessional") ? new QRisc(forProperty("riscProfessional")) : null;
        this.valoracio = inits.isInitialized("valoracio") ? new QValoracio(forProperty("valoracio")) : null;
    }

}

