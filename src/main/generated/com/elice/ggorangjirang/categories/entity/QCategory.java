package com.elice.ggorangjirang.categories.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCategory is a Querydsl query type for Category
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCategory extends EntityPathBase<Category> {

    private static final long serialVersionUID = 1541755966L;

    public static final QCategory category = new QCategory("category");

    public final StringPath categoryName = createString("categoryName");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ListPath<com.elice.ggorangjirang.subcategories.entity.Subcategory, com.elice.ggorangjirang.subcategories.entity.QSubcategory> subcategories = this.<com.elice.ggorangjirang.subcategories.entity.Subcategory, com.elice.ggorangjirang.subcategories.entity.QSubcategory>createList("subcategories", com.elice.ggorangjirang.subcategories.entity.Subcategory.class, com.elice.ggorangjirang.subcategories.entity.QSubcategory.class, PathInits.DIRECT2);

    public QCategory(String variable) {
        super(Category.class, forVariable(variable));
    }

    public QCategory(Path<? extends Category> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCategory(PathMetadata metadata) {
        super(Category.class, metadata);
    }

}

