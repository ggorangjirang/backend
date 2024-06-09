package com.elice.ggorangjirang.subcategories.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QSubcategory is a Querydsl query type for Subcategory
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSubcategory extends EntityPathBase<Subcategory> {

    private static final long serialVersionUID = 1615814764L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSubcategory subcategory = new QSubcategory("subcategory");

    public final com.elice.ggorangjirang.categories.entity.QCategory category;

    public final NumberPath<Long> categoryId = createNumber("categoryId", Long.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ListPath<com.elice.ggorangjirang.products.entity.Product, com.elice.ggorangjirang.products.entity.QProduct> products = this.<com.elice.ggorangjirang.products.entity.Product, com.elice.ggorangjirang.products.entity.QProduct>createList("products", com.elice.ggorangjirang.products.entity.Product.class, com.elice.ggorangjirang.products.entity.QProduct.class, PathInits.DIRECT2);

    public final StringPath subcategoryName = createString("subcategoryName");

    public QSubcategory(String variable) {
        this(Subcategory.class, forVariable(variable), INITS);
    }

    public QSubcategory(Path<? extends Subcategory> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QSubcategory(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QSubcategory(PathMetadata metadata, PathInits inits) {
        this(Subcategory.class, metadata, inits);
    }

    public QSubcategory(Class<? extends Subcategory> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.category = inits.isInitialized("category") ? new com.elice.ggorangjirang.categories.entity.QCategory(forProperty("category")) : null;
    }

}

