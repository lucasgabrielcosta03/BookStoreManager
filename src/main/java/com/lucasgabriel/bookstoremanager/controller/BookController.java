package com.lucasgabriel.bookstoremanager.controller;

import com.lucasgabriel.bookstoremanager.Service.BookService;
import com.lucasgabriel.bookstoremanager.dto.MessageResponseDTO;
import com.lucasgabriel.bookstoremanager.entity.Book;
import com.lucasgabriel.bookstoremanager.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/books")
public class BookController {
    private BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public MessageResponseDTO create( @RequestBody @Validated Book book){
       return bookService.create(book);
    }
    @GetMapping("/test")
    public String tes(){
        return "Controller na rota test funcinando corretamente";
    }
}
