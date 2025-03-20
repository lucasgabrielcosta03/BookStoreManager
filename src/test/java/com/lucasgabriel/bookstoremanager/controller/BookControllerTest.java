package com.lucasgabriel.bookstoremanager.controller;

import com.lucasgabriel.bookstoremanager.Service.BookService;
import com.lucasgabriel.bookstoremanager.dto.BookDTO;
import com.lucasgabriel.bookstoremanager.dto.MessageResponseDTO;
import com.lucasgabriel.bookstoremanager.utils.BookUtils;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

@ExtendWith(MockitoExtension.class)
public class BookControllerTest {
    private MockMvc mockMvc;

    @Mock
    private BookService bookService;

    @InjectMocks
    private BookController bookController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(bookController)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .setViewResolvers(((viewName, locale) -> new MappingJackson2JsonView()))
                .build();
    }

    @Test
    void testWhenPOSTisCalledThenABooksShouldBeCreated() throws Exception {
        BookDTO bookDTO = BookUtils.createFakeBookDTO();
        MessageResponseDTO expectedMessageResponse = MessageResponseDTO.builder()
                .message("Book Created with ID "+ bookDTO.getId())
                .build();

        Mockito.when(bookService.create(bookDTO)).thenReturn(expectedMessageResponse);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content(BookUtils.asJsonString(bookDTO)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message", Is.is(expectedMessageResponse.getMessage())));

    }

    @Test
    void testWhenPOSTwithInvalidISBNIsCalledThenBadRequestShouldBeReturned() throws Exception {
        BookDTO bookDTO = BookUtils.createFakeBookDTO();
        bookDTO.setIsbn("ISBN is invalid");

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(BookUtils.asJsonString(bookDTO)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
}
