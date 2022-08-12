package com.company.FinalProject.entity;

import com.company.FinalProject.dto.Author.AuthorDTO;
import com.company.FinalProject.dto.Author.AuthorResponseDTO;
import com.company.FinalProject.dto.Author.AuthorShortDTO;
import com.company.FinalProject.dto.Book.BookShortDTO;
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
    @ManyToMany
    @JoinTable(
            name = "authors_genres",
            joinColumns = @JoinColumn(name = "author_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id"))
    private List<Genre> genresList;
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

    public AuthorResponseDTO convertToResponseDTO(){
        AuthorResponseDTO authorResponseDTO=new AuthorResponseDTO();
        authorResponseDTO.setId(this.getId());
        authorResponseDTO.setName(this.getName());
        authorResponseDTO.setSurname(this.getSurname());
        authorResponseDTO.setPatronymic(this.getPatronymic());
        authorResponseDTO.setDateOfBirth(this.getDateOfBirth());
        authorResponseDTO.setBooksList(this.getAuthorsBooksList().stream().map(Book::convertToShortDTO).toList());
        authorResponseDTO.setGenreList(this.getGenresList().stream().map(Genre::convertToDto).toList());
        return authorResponseDTO;
    }
    public AuthorShortDTO convertToShortDTO(){
        AuthorShortDTO authorShortDTO = new AuthorShortDTO();
        authorShortDTO.setId(this.getId());
        authorShortDTO.setName(this.getName());
        authorShortDTO.setPatronymic(this.getPatronymic());
        authorShortDTO.setSurname(this.getSurname());
        return authorShortDTO;
    }
}
