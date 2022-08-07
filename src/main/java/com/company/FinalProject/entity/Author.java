package com.company.FinalProject.entity;

import com.company.FinalProject.dto.Author.AuthorDTO;
import com.company.FinalProject.dto.Author.AuthorResponseDTO;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@Table(name="AUTHOR")
public class Author {
    @Id
    @SequenceGenerator(
            name="author_sequence",
            sequenceName="author_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "author_sequence"
    )
    @Column(name="author_id")
    private Long id;
    @Column(name = "surname")
    private String surname;
    @Column(name = "name")
    private String name;
    @Column(name = "patronymic")
    private String patronymic;
    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;
    @ManyToMany
    @JoinTable(
            name = "authors_book",
            joinColumns = @JoinColumn(name = "author_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id"))
    private List<Book> authorsBooksList;
    @ManyToMany
    @JoinTable(
            name = "author_genre",
            joinColumns = @JoinColumn(name = "author_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id"))
    private List<Genre> authorsGenresList;
//getgenres по книгам и собрать жанры
    public AuthorDTO convertToDto() {
        AuthorDTO authorDTO = new AuthorDTO();
        authorDTO.setName(this.getName());
        authorDTO.setSurname(this.getSurname());
        authorDTO.setId(this.getId());
        authorDTO.setPatronymic(this.getPatronymic());
        authorDTO.setAuthorsGenresList(this.getAuthorsGenresList().stream().map(Genre::getId).toList());
        authorDTO.setAuthorsBooksList(this.getAuthorsBooksList().stream().map(Book::getId).toList());
        authorDTO.setDateOfBirth(this.getDateOfBirth());

        return authorDTO;
    }

    public AuthorResponseDTO convertToResponseDTO(){
        AuthorResponseDTO authorResponseDTO=new AuthorResponseDTO();
        authorResponseDTO.setName(this.getName());
        authorResponseDTO.setSurname(this.getSurname());
        authorResponseDTO.setId(this.getId());
        authorResponseDTO.setGenreList(this.getAuthorsGenresList().stream().map(Genre::convertToDto).toList());
        authorResponseDTO.setPatronymic(this.getPatronymic());
        authorResponseDTO.setDateOfBirth(this.getDateOfBirth());
        return authorResponseDTO;
    }
}
