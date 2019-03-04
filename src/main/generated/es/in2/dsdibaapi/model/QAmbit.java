package es.in2.dsdibaapi.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QAmbit is a Querydsl query type for Ambit
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QAmbit extends EntityPathBase<Ambit> {

    private static final long serialVersionUID = 2018180788L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QAmbit ambit = new QAmbit("ambit");

    public final StringPath descripcio = createString("descripcio");

    public final ListPath<Entorn, QEntorn> entorn = this.<Entorn, QEntorn>createList("entorn", Entorn.class, QEntorn.class, PathInits.DIRECT2);

    public final ListPath<Factor, QFactor> factorGravetat = this.<Factor, QFactor>createList("factorGravetat", Factor.class, QFactor.class, PathInits.DIRECT2);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Double> risc = createNumber("risc", Double.class);

    public final NumberPath<Double> valAltrisc = createNumber("valAltrisc", Double.class);

    public final NumberPath<Double> valRisc = createNumber("valRisc", Double.class);

    public final NumberPath<Double> valVulnerabilitat = createNumber("valVulnerabilitat", Double.class);

    public final QVersioModel versioModel;

    public final NumberPath<Double> vulnerabilitat = createNumber("vulnerabilitat", Double.class);

    public QAmbit(String variable) {
        this(Ambit.class, forVariable(variable), INITS);
    }

    public QAmbit(Path<? extends Ambit> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QAmbit(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QAmbit(PathMetadata metadata, PathInits inits) {
        this(Ambit.class, metadata, inits);
    }

    public QAmbit(Class<? extends Ambit> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.versioModel = inits.isInitialized("versioModel") ? new QVersioModel(forProperty("versioModel")) : null;
    }

}

