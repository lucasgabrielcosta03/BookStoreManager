package com.lucasgabriel.bookstoremanager.controller;

import com.lucasgabriel.bookstoremanager.Service.BookService;
import com.lucasgabriel.bookstoremanager.dto.BookDTO;
import com.lucasgabriel.bookstoremanager.dto.MessageResponseDTO;
import com.lucasgabriel.bookstoremanager.entity.Book;
import com.lucasgabriel.bookstoremanager.repository.BookRepository;
import jakarta.validation.Valid;
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
    public MessageResponseDTO create(@RequestBody @Valid BookDTO bookDTO){
       return bookService.create(bookDTO);
    }
    @GetMapping("/test")
    public String tes(){
        return "Controller na rota test funcinando corretamente";
    }
}
//Proxima aula 21 Validação de dados: criação de DTO para a validação dos dados de cadastro