package com.haisi.java.testfeatures.data.repository;

import com.haisi.java.testfeatures.data.entity.CategoryEntity;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<CategoryEntity, Long> {
}
