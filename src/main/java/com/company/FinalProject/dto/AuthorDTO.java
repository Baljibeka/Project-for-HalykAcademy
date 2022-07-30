package com.company.FinalProject.dto;

import com.company.FinalProject.entity.Author;
import com.company.FinalProject.entity.Book;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthorDTO {
    private long id;
    private String surname;
    private String name;
    private String patronymic;
    private LocalDate dateOfBirth;
    private List<BookResponseDTO> authorsBooksList;
    private List<GenreDTO> authorsGenresList;

    public Author convertToEntity() {
        Author author = new Author();
        author.setName(this.getName());
        author.setId(this.getId());
        if(this.authorsBooksList!=null)
            author.setAuthorsBooksList(this.getAuthorsBooksList().stream().map(BookResponseDTO::convertToEntity).toList());
        if(this.getAuthorsGenresList()!=null)
            author.setAuthorsGenresList(this.getAuthorsGenresList().stream().map(GenreDTO::convertToEntity).toList());
        author.setPatronymic(this.getPatronymic());
        author.setSurname(this.getSurname());
        author.setDateOfBirth(this.getDateOfBirth());

        return author;
    }
}
