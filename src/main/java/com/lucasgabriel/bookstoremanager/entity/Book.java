package com.lucasgabriel.bookstoremanager.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false, unique = true)
    private String title;

    @Column(nullable = false)
    private Integer page;

    @Column(nullable = false)
    private Integer chapters;

    @Column(nullable = false)
    private String isbn;

    @Column(name = "publisher_name",nullable = false, unique = true)
    private String publisherName;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}) //Relacionamentos munintos para um // Insere os dois tanto livro e autor
    @JoinColumn(name = "author_id")// relacionamento para a tabela de autor se sera a chave estrengeira author_id
    private Author author;

}
