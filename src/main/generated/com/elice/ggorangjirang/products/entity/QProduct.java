package com.elice.ggorangjirang.products.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QProduct is a Querydsl query type for Product
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QProduct extends EntityPathBase<Product> {

    private static final long serialVersionUID = 1029335975L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QProduct product = new QProduct("product");

    public final ListPath<com.elice.ggorangjirang.carts.entity.CartItem, com.elice.ggorangjirang.carts.entity.QCartItem> cartItems = this.<com.elice.ggorangjirang.carts.entity.CartItem, com.elice.ggorangjirang.carts.entity.QCartItem>createList("cartItems", com.elice.ggorangjirang.carts.entity.CartItem.class, com.elice.ggorangjirang.carts.entity.QCartItem.class, PathInits.DIRECT2);

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final StringPath description = createString("description");

    public final StringPath descriptionImageUrl = createString("descriptionImageUrl");

    public final NumberPath<Float> discountRate = createNumber("discountRate", Float.class);

    public final DatePath<java.time.LocalDate> expirationDate = createDate("expirationDate", java.time.LocalDate.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath isDeleted = createBoolean("isDeleted");

    public final BooleanPath isSoldOut = createBoolean("isSoldOut");

    public final StringPath name = createString("name");

    public final NumberPath<Integer> orderCount = createNumber("orderCount", Integer.class);

    public final ListPath<com.elice.ggorangjirang.orders.entity.OrderItem, com.elice.ggorangjirang.orders.entity.QOrderItem> orderItems = this.<com.elice.ggorangjirang.orders.entity.OrderItem, com.elice.ggorangjirang.orders.entity.QOrderItem>createList("orderItems", com.elice.ggorangjirang.orders.entity.OrderItem.class, com.elice.ggorangjirang.orders.entity.QOrderItem.class, PathInits.DIRECT2);

    public final NumberPath<Integer> price = createNumber("price", Integer.class);

    public final StringPath productImageUrl = createString("productImageUrl");

    public final ListPath<com.elice.ggorangjirang.reviews.entity.Review, com.elice.ggorangjirang.reviews.entity.QReview> reviews = this.<com.elice.ggorangjirang.reviews.entity.Review, com.elice.ggorangjirang.reviews.entity.QReview>createList("reviews", com.elice.ggorangjirang.reviews.entity.Review.class, com.elice.ggorangjirang.reviews.entity.QReview.class, PathInits.DIRECT2);

    public final NumberPath<Integer> stock = createNumber("stock", Integer.class);

    public final com.elice.ggorangjirang.subcategories.entity.QSubcategory subcategory;

    public final NumberPath<Long> subcategoryId = createNumber("subcategoryId", Long.class);

    public final DateTimePath<java.time.LocalDateTime> updatedAt = createDateTime("updatedAt", java.time.LocalDateTime.class);

    public final NumberPath<Integer> viewCount = createNumber("viewCount", Integer.class);

    public QProduct(String variable) {
        this(Product.class, forVariable(variable), INITS);
    }

    public QProduct(Path<? extends Product> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QProduct(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QProduct(PathMetadata metadata, PathInits inits) {
        this(Product.class, metadata, inits);
    }

    public QProduct(Class<? extends Product> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.subcategory = inits.isInitialized("subcategory") ? new com.elice.ggorangjirang.subcategories.entity.QSubcategory(forProperty("subcategory"), inits.get("subcategory")) : null;
    }

}

