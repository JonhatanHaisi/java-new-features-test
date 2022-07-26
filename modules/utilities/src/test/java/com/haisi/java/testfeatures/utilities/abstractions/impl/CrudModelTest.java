package com.haisi.java.testfeatures.utilities.abstractions.impl;

import com.haisi.java.testfeatures.utilities.abstractions.CrudModel;
import com.haisi.java.testfeatures.utilities.mapping.Mapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.data.repository.CrudRepository;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class CrudModelTest {

    @Mock
    private CrudRepository<String, Integer> repository;

    @Mock
    private Mapper mapper;

    private CrudModel<Integer> crudModel;

    @BeforeEach
    void setup() {
        crudModel = CrudModelImpl.<String, Integer>builder()
                .repository(repository)
                .entityType(String.class)
                .mapper(mapper)
                .build();
    }

    @Test
    @DisplayName("Test auto generated toString method")
    void testToString() {
        assertNotNull(crudModel.toString(), "toString with null value");
    }

    @Test
    @DisplayName("Save an entity with custom return")
    void save() {
        var include = "Include";
        var mappedEntity = "Mapped Entity";
        var savedEntity = "Saved Entity";
        var mappedResult = "Mapped Result";

        when(mapper.map(include, String.class))
                .thenReturn(mappedEntity);
        when(repository.save(mappedEntity))
                .thenReturn(savedEntity);
        when(mapper.map(savedEntity, String.class))
                .thenReturn(mappedResult);

        var result = crudModel.save(include, String.class);

        verify(repository, times(1)).save(mappedEntity);
        assertEquals(mappedResult, result, "Crud Saving not working");
    }

    @Test
    @DisplayName("Save multiple entities with custom return")
    void saveAll() {
        var include = singletonList("Include");
        var mappedEntity = singletonList("Mapped Entity");
        var savedEntity = singletonList("Saved Entity");
        var mappedResult = singletonList("Mapped Result");

        when(mapper.mapAll(include, String.class))
                .thenReturn(mappedEntity);
        when(repository.saveAll(mappedEntity))
                .thenReturn(savedEntity);
        when(mapper.mapAll(savedEntity, String.class))
                .thenReturn(mappedResult);

        var result = crudModel.saveAll(include, String.class);

        verify(repository, times(1)).saveAll(mappedEntity);
        assertEquals(mappedResult, result, "Crud Saving not working");
    }

    @Test
    @DisplayName("Save an entity without any return")
    void save_void() {
        var include = "Include";
        var mappedEntity = "Mapped Entity";
        var savedEntity = "Saved Entity";

        when(mapper.map(include, String.class))
                .thenReturn(mappedEntity);
        when(repository.save(mappedEntity))
                .thenReturn(savedEntity);

        crudModel.save(include);

        verify(repository, times(1)).save(mappedEntity);
    }

    @Test
    @DisplayName("Save multiple entities without any return")
    void saveAll_void() {
        var include = singletonList("Include");
        var mappedEntity = singletonList("Mapped Entity");
        var savedEntity = singletonList("Saved Entity");

        when(mapper.mapAll(include, String.class))
                .thenReturn(mappedEntity);
        when(repository.saveAll(mappedEntity))
                .thenReturn(savedEntity);

        crudModel.saveAll(include);

        verify(repository, times(1)).saveAll(mappedEntity);
    }

    @Test
    @DisplayName("Delete an entity")
    void delete() {
        var delete = "Delete";
        var mappedEntity = "Mapped Entity";

        when(mapper.map(delete, String.class))
                .thenReturn(mappedEntity);

        crudModel.delete(delete);

        verify(repository, times(1)).delete(mappedEntity);
    }

    @Test
    @DisplayName("Delete multiple entities")
    void deleteAll() {
        var delete = singletonList("Delete");
        var mappedEntity = singletonList("Mapped Entity");

        when(mapper.mapAll(delete, String.class))
                .thenReturn(mappedEntity);

        crudModel.deleteAll(delete);

        verify(repository, times(1)).deleteAll(mappedEntity);
    }

    @Test
    @DisplayName("Delete an entity by id")
    void deleteById() {
        crudModel.deleteById(1);

        verify(repository, times(1)).deleteById(1);
    }

    @Test
    @DisplayName("Delete multiple entities by id")
    void deleteAllById() {
        crudModel.deleteAllById(singletonList(1));

        verify(repository, times(1)).deleteAllById(singletonList(1));
    }

    @Test
    @DisplayName("Find an entity by id and map the result")
    void findById() {
        var found = Optional.of("Result Entity");
        var mappedEntity = "Mapped Entity";

        when(repository.findById(1))
                .thenReturn(found);
        when(mapper.map("Result Entity", String.class))
                .thenReturn(mappedEntity);

        var result = crudModel.findById(1, String.class)
                .orElse(null);

        assertEquals(mappedEntity, result, "Returned wrong result");
    }

    @Test
    @DisplayName("Find multiple entities by id and map the result")
    void findAllById() {
        var found = singletonList("Result Entity");
        var mappedEntity = singletonList("Mapped Entity");

        when(repository.findAllById(singletonList(1)))
                .thenReturn(found);
        when(mapper.mapAll(found, String.class))
                .thenReturn(mappedEntity);

        var result = crudModel.findByIds(singletonList(1), String.class);

        assertEquals(mappedEntity, result, "Returned wrong result");
    }

    @Test
    @DisplayName("Find all entities and map the result")
    void findAll() {
        var found = singletonList("Result Entity");
        var mappedEntity = singletonList("Mapped Entity");

        when(repository.findAll())
                .thenReturn(found);
        when(mapper.mapAll(found, String.class))
                .thenReturn(mappedEntity);

        var result = crudModel.findAll(String.class);

        assertEquals(mappedEntity, result, "Returned wrong result");
    }

    @Test
    @DisplayName("Test the method toString from builder for coverage")
    void builder_toString() {
        assertNotNull(CrudModelImpl.builder().toString());
    }

}
