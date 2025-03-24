package com.lucasgabriel.bookstoremanager.Service;

import com.lucasgabriel.bookstoremanager.dto.BookDTO;
import com.lucasgabriel.bookstoremanager.dto.MessageResponseDTO;
import com.lucasgabriel.bookstoremanager.entity.Book;
import com.lucasgabriel.bookstoremanager.exception.BookNotFundException;
import com.lucasgabriel.bookstoremanager.mapper.BookMapper;
import com.lucasgabriel.bookstoremanager.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Service // Sera gerenciada pelo SPRING
public class BookService {
    private BookRepository bookRepository;
    private final BookMapper bookMapper  = BookMapper.INSTANCE;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    @PostMapping
    public MessageResponseDTO create(BookDTO bookDTO){
        Book bookToSave = bookMapper.toModel(bookDTO);
        Book savedBook = bookRepository.save(bookToSave);

        return MessageResponseDTO.builder().
                message("Book Created with ID "+ savedBook.getId()).build();
    }

    public  BookDTO findById(long id) throws BookNotFundException {
      Book optionalBook = bookRepository.findById(id)
              .orElseThrow(() -> new BookNotFundException(id));

       return bookMapper.toDTO(optionalBook);

    }
}
