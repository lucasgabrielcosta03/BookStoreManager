package com.lucasgabriel.bookstoremanager.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class BookNotFundException extends Exception  {
    public BookNotFundException(long id) {
        super(String.format("Book with Id not found ", id));
    }
}
