package es.in2.dsdibaapi.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QEconomia is a Querydsl query type for Economia
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QEconomia extends EntityPathBase<Economia> {

    private static final long serialVersionUID = 1144606144L;

    public static final QEconomia economia = new QEconomia("economia");

    public final SimplePath<es.in2.dsdibaapi.model.id.EconomiaId> id = createSimple("id", es.in2.dsdibaapi.model.id.EconomiaId.class);

    public QEconomia(String variable) {
        super(Economia.class, forVariable(variable));
    }

    public QEconomia(Path<? extends Economia> path) {
        super(path.getType(), path.getMetadata());
    }

    public QEconomia(PathMetadata metadata) {
        super(Economia.class, metadata);
    }

}

