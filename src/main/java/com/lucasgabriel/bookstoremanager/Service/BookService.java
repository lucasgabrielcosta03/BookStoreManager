package com.lucasgabriel.bookstoremanager.Service;

import com.lucasgabriel.bookstoremanager.dto.MessageResponseDTO;
import com.lucasgabriel.bookstoremanager.entity.Book;
import com.lucasgabriel.bookstoremanager.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Service // Sera gerenciada pelo SPRING
public class BookService {
    private BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    @PostMapping
    public MessageResponseDTO create(Book book){
        Book savedBook = bookRepository.save(book);
        return MessageResponseDTO.builder().
                message("Book Created with ID "+ savedBook.getId())
                .build();
    }
}
