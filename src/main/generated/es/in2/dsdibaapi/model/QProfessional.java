package es.in2.dsdibaapi.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QProfessional is a Querydsl query type for Professional
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QProfessional extends EntityPathBase<Professional> {

    private static final long serialVersionUID = 1938849140L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QProfessional professional = new QProfessional("professional");

    public final StringPath cognom1 = createString("cognom1");

    public final StringPath cognom2 = createString("cognom2");

    public final ListPath<Expedient, QExpedient> expedient = this.<Expedient, QExpedient>createList("expedient", Expedient.class, QExpedient.class, PathInits.DIRECT2);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QMunicipi municipi;

    public final StringPath nom = createString("nom");

    public final StringPath password = createString("password");

    public final SetPath<Rol, QRol> rol = this.<Rol, QRol>createSet("rol", Rol.class, QRol.class, PathInits.DIRECT2);

    public final StringPath username = createString("username");

    public QProfessional(String variable) {
        this(Professional.class, forVariable(variable), INITS);
    }

    public QProfessional(Path<? extends Professional> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QProfessional(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QProfessional(PathMetadata metadata, PathInits inits) {
        this(Professional.class, metadata, inits);
    }

    public QProfessional(Class<? extends Professional> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.municipi = inits.isInitialized("municipi") ? new QMunicipi(forProperty("municipi")) : null;
    }

}

