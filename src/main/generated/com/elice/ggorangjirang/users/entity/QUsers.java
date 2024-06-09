package com.elice.ggorangjirang.users.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUsers is a Querydsl query type for Users
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUsers extends EntityPathBase<Users> {

    private static final long serialVersionUID = 1814405770L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUsers users = new QUsers("users");

    public final QAddress address;

    public final com.elice.ggorangjirang.carts.entity.QCart cart;

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> deletedAt = createDateTime("deletedAt", java.time.LocalDateTime.class);

    public final StringPath email = createString("email");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final StringPath password = createString("password");

    public final StringPath phoneNumber = createString("phoneNumber");

    public final StringPath refreshToken = createString("refreshToken");

    public final ListPath<com.elice.ggorangjirang.reviews.entity.Review, com.elice.ggorangjirang.reviews.entity.QReview> reviews = this.<com.elice.ggorangjirang.reviews.entity.Review, com.elice.ggorangjirang.reviews.entity.QReview>createList("reviews", com.elice.ggorangjirang.reviews.entity.Review.class, com.elice.ggorangjirang.reviews.entity.QReview.class, PathInits.DIRECT2);

    public final EnumPath<Role> role = createEnum("role", Role.class);

    public final StringPath socialId = createString("socialId");

    public final DateTimePath<java.time.LocalDateTime> updatedAt = createDateTime("updatedAt", java.time.LocalDateTime.class);

    public QUsers(String variable) {
        this(Users.class, forVariable(variable), INITS);
    }

    public QUsers(Path<? extends Users> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUsers(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUsers(PathMetadata metadata, PathInits inits) {
        this(Users.class, metadata, inits);
    }

    public QUsers(Class<? extends Users> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.address = inits.isInitialized("address") ? new QAddress(forProperty("address")) : null;
        this.cart = inits.isInitialized("cart") ? new com.elice.ggorangjirang.carts.entity.QCart(forProperty("cart"), inits.get("cart")) : null;
    }

}

