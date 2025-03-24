package com.lucasgabriel.bookstoremanager.service;

import com.lucasgabriel.bookstoremanager.Service.BookService;
import com.lucasgabriel.bookstoremanager.dto.BookDTO;
import com.lucasgabriel.bookstoremanager.entity.Book;
import com.lucasgabriel.bookstoremanager.exception.BookNotFundException;
import com.lucasgabriel.bookstoremanager.repository.BookRepository;
import com.lucasgabriel.bookstoremanager.utils.BookUtils;
import jdk.jfr.Enabled;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;



import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {
    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    @Test
    void whenGivenExistingIdThenReturnThisBook() throws BookNotFundException {
        Book expectedFoundBook = BookUtils.createFakeBook();
        when(bookRepository.findById(expectedFoundBook.getId())).thenReturn(Optional.of(expectedFoundBook));

        BookDTO bookDTO = bookService.findById(expectedFoundBook.getId());

        Assertions.assertEquals(expectedFoundBook.getTitle(), bookDTO.getTitle());
        Assertions.assertEquals(expectedFoundBook.getIsbn(), bookDTO.getIsbn());
        Assertions.assertEquals(expectedFoundBook.getPublisherName(), bookDTO.getPublisherName());
    }

    @Test
    void whenGivenExistingIdThenNotFindThrowAnException() {
        var invalido = 10L;
        when(bookRepository.findById(invalido))
                .thenReturn(Optional.ofNullable(any(Book.class)));

        assertThrows(BookNotFundException.class, () -> bookService.findById(invalido));
    }
}
