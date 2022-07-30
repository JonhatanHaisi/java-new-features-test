package com.haisi.java.testfeatures.data.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@Entity
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "CATEGORY")
public class CategoryEntity {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @ManyToMany(mappedBy = "categories")
    private List<ProductEntity> products;

}
