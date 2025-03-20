package com.lucasgabriel.bookstoremanager.utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.github.javafaker.Faker;
import com.lucasgabriel.bookstoremanager.dto.BookDTO;
import com.lucasgabriel.bookstoremanager.entity.Book;

import static com.lucasgabriel.bookstoremanager.utils.AuthorUtils.createFakeAuthor;
import static com.lucasgabriel.bookstoremanager.utils.AuthorUtils.createFakeAuthorDTO;

public class BookUtils {
    private static final Faker faker = Faker.instance();
    public static BookDTO createFakeBookDTO(){
        return BookDTO.builder()
                .id(faker.number().randomNumber())
                .title(faker.book().title())
                .page(faker.number().numberBetween(0, 200))
                .chapters(faker.number().numberBetween(1, 20))
                .isbn("0-598-53436-8")
                .publisherName(faker.book().publisher())
                .authorDTO(createFakeAuthorDTO())
                .build();
    }
    public static Book createFakeBook(){
        return Book.builder()
                .id(faker.number().randomNumber())
                .title(faker.book().title())
                .page(faker.number().numberBetween(0, 200))
                .chapters(faker.number().numberBetween(1, 20))
                .isbn("0-598-53436-8")
                .publisherName(faker.book().publisher())
                .author(createFakeAuthor())
                .build();
    }

    public static String asJsonString(BookDTO bookDTO){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
            objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
            objectMapper.registerModules(new JavaTimeModule());
            return objectMapper.writeValueAsString(bookDTO);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
