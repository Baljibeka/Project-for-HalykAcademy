package com.company.FinalProject.dto.Author;

import com.company.FinalProject.entity.Author;
import com.company.FinalProject.entity.Book;
import com.company.FinalProject.entity.Genre;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorDTO {
    private long id;
    private String surname;
    private String name;
    private String patronymic;
    private LocalDate dateOfBirth;
    private List<Long> authorsBooksList;
    private List<Long> authorsGenresList;

    public Author convertToEntity(List<Genre> genreList, List<Book> bookList) {
        Author author = new Author();
        author.setName(this.getName());
        author.setId(this.getId());
        author.setAuthorsBooksList(bookList);
        author.setAuthorsGenresList(genreList);
        author.setPatronymic(this.getPatronymic());
        author.setSurname(this.getSurname());
        author.setDateOfBirth(this.getDateOfBirth());

        return author;
    }
}
