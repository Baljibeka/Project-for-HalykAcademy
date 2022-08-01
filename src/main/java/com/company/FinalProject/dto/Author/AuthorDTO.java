package com.company.FinalProject.dto.Author;

import com.company.FinalProject.dto.Book.BookIdDTO;
import com.company.FinalProject.dto.Genre.GenreDTO;
import com.company.FinalProject.dto.Genre.GenreIdDTO;
import com.company.FinalProject.entity.Author;
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
    private List<BookIdDTO> authorsBooksList;
    private List<GenreIdDTO> authorsGenresList;

    public Author convertToEntity() {
        Author author = new Author();
        author.setName(this.getName());
        author.setId(this.getId());
        if(this.authorsBooksList!=null)
            author.setAuthorsBooksList(this.getAuthorsBooksList().stream().map(BookIdDTO::convertToEntity).toList());
        if(this.getAuthorsGenresList()!=null)
            author.setAuthorsGenresList(this.getAuthorsGenresList().stream().map(GenreIdDTO::convertToEntity).toList());
        author.setPatronymic(this.getPatronymic());
        author.setSurname(this.getSurname());
        author.setDateOfBirth(this.getDateOfBirth());

        return author;
    }
}
