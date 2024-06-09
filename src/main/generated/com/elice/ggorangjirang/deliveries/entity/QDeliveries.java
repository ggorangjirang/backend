package com.elice.ggorangjirang.deliveries.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QDeliveries is a Querydsl query type for Deliveries
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QDeliveries extends EntityPathBase<Deliveries> {

    private static final long serialVersionUID = -1593127224L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QDeliveries deliveries = new QDeliveries("deliveries");

    public final DateTimePath<java.time.LocalDateTime> arrivalDate = createDateTime("arrivalDate", java.time.LocalDateTime.class);

    public final StringPath detailAddress = createString("detailAddress");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final com.elice.ggorangjirang.orders.entity.QOrder order;

    public final StringPath phoneNumber = createString("phoneNumber");

    public final StringPath request = createString("request");

    public final DateTimePath<java.time.LocalDateTime> startDate = createDateTime("startDate", java.time.LocalDateTime.class);

    public final StringPath status = createString("status");

    public final StringPath streetAddress = createString("streetAddress");

    public final StringPath zipcode = createString("zipcode");

    public QDeliveries(String variable) {
        this(Deliveries.class, forVariable(variable), INITS);
    }

    public QDeliveries(Path<? extends Deliveries> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QDeliveries(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QDeliveries(PathMetadata metadata, PathInits inits) {
        this(Deliveries.class, metadata, inits);
    }

    public QDeliveries(Class<? extends Deliveries> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.order = inits.isInitialized("order") ? new com.elice.ggorangjirang.orders.entity.QOrder(forProperty("order"), inits.get("order")) : null;
    }

}

