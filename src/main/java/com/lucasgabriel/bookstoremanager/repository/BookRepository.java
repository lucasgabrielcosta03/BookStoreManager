package com.lucasgabriel.bookstoremanager.repository;

import com.lucasgabriel.bookstoremanager.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long>{ //Gerenciar nosso coneção com o banco de Dados
    
}
