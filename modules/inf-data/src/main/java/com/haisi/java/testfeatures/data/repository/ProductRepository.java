package com.haisi.java.testfeatures.data.repository;

import com.haisi.java.testfeatures.data.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
}
