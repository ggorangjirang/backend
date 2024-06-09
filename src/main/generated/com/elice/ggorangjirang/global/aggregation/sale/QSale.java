package com.elice.ggorangjirang.global.aggregation.sale;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QSale is a Querydsl query type for Sale
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSale extends EntityPathBase<Sale> {

    private static final long serialVersionUID = -1172521698L;

    public static final QSale sale = new QSale("sale");

    public final DateTimePath<java.time.LocalDateTime> dateTime = createDateTime("dateTime", java.time.LocalDateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> totalOrders = createNumber("totalOrders", Long.class);

    public final NumberPath<Long> totalSales = createNumber("totalSales", Long.class);

    public QSale(String variable) {
        super(Sale.class, forVariable(variable));
    }

    public QSale(Path<? extends Sale> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSale(PathMetadata metadata) {
        super(Sale.class, metadata);
    }

}

