package com.lucasgabriel.bookstoremanager.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
  @Column(nullable = false, unique = true)
    private String nome;
    @Column(nullable = false)
    private Integer age;

    @Column(nullable = false)
    private String country;

}
