package com.lucasgabriel.bookstoremanager.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class    BookDTO {
    private long id;

    @NotBlank
    @Size(max = 200)
    private String title;

    @NotNull
    private Integer page;

    @NotNull
    private Integer chapters;

    @NotBlank
    @Size(max = 200)
    @Pattern(regexp = "^(\\d{10}|\\d{13})$", message = "ISBN format invalid")
    private String isbn;

    @NotBlank
    @Size(max = 200)
    private String publisherName;

    @Valid
    @NotNull
    private AuthorDTO authorDTO;

}
