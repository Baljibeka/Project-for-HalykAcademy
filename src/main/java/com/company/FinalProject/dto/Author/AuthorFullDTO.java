package com.company.FinalProject.dto.Author;

import com.company.FinalProject.dto.Book.BookShortDTO;
import com.company.FinalProject.dto.Genre.GenreDTO;
import com.company.FinalProject.entity.Author;

import java.time.LocalDate;
import java.util.List;

import com.company.FinalProject.entity.Book;
import com.company.FinalProject.entity.Genre;
import lombok.*;

@Data
public class AuthorFullDTO {
    private long id;
    private String surname;
    private String name;
    private String patronymic;
    private LocalDate dateOfBirth;
    private List<BookShortDTO> booksList;
    private List<GenreDTO> genreList;
    public Author convertToEntity(){
        Author author = new Author();
        author.setName(this.getName());
        author.setId(this.getId());
        author.setPatronymic(this.getPatronymic());
        author.setSurname(this.getSurname());
        author.setDateOfBirth(this.getDateOfBirth());
        author.setAuthorsBooksList(this.getBooksList().stream().map(BookShortDTO::convertToEntity).toList());
        author.setGenresList(this.getGenreList().stream().map(GenreDTO::convertToEntity).toList());
        return author;
    }
}
