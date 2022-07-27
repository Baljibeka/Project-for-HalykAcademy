package com.company.FinalProject.dto;

import com.company.FinalProject.entity.Author;
import com.company.FinalProject.entity.Book;

import java.time.LocalDate;
import java.util.List;

public class AuthorDTO {
    private long id;
    private String surname;
    private String name;
    private String patronymic;
    private LocalDate dateOfBirth;
    private List<BookDTO> authorsBooksList;
    private List<GenreDTO> authorsGenresList;

    public List<GenreDTO> getAuthorsGenresList() {
        return authorsGenresList;
    }

    public void setAuthorsGenresList(List<GenreDTO> authorsGenresList) {
        this.authorsGenresList = authorsGenresList;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public List<BookDTO> getAuthorsBooksList() {
        return authorsBooksList;
    }

    public void setAuthorsBooksList(List<BookDTO> authorsBooksList) {
        this.authorsBooksList = authorsBooksList;
    }
    public Author convertToEntity() {
        Author author = new Author();
        author.setName(this.getName());
        author.setId(this.getId());
        author.setAuthorsBooksList(this.getAuthorsBooksList().stream().map(BookDTO::convertToEntity).toList());
        author.setAuthorsGenresList(this.getAuthorsGenresList().stream().map(GenreDTO::convertToEntity).toList());
        author.setPatronymic(this.getPatronymic());
        author.setSurname(this.getSurname());
        author.setDateOfBirth(this.getDateOfBirth());

        return author;
    }
}
