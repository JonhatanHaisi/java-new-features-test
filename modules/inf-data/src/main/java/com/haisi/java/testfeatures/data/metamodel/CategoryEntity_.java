package com.haisi.java.testfeatures.data.metamodel;

import com.haisi.java.testfeatures.data.entity.CategoryEntity;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(CategoryEntity.class)
public class CategoryEntity_ {

    public static volatile SingularAttribute<CategoryEntity, String> name;

}
