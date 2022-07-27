package com.haisi.java.testfeatures.data.repository;

import com.haisi.java.testfeatures.data.entity.CategoryEntity;
import com.haisi.java.testfeatures.data.tests.TestBase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;


class CategoryRepositoryTest extends TestBase {

    @Autowired
    private CategoryRepository repository;

    @BeforeEach
    public void setup() {
        var category = CategoryEntity.builder()
            .name("Category")
            .build();

        repository.save(category);
    }

    @AfterEach
    public void tearDown() {
        repository.deleteAll();
    }

    @Test
    @DisplayName("Sample test to validate queries")
    void findAll() {
        var data = (List<CategoryEntity>) repository.findAll();

        assertFalse(data.isEmpty(), "List should not be empty");
        assertEquals("Category", data.get(0).getName(), "Wrong result");
    }

}
