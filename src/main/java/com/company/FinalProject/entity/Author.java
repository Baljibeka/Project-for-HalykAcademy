package com.company.FinalProject.entity;

import com.company.FinalProject.dto.AuthorDTO;
import com.company.FinalProject.dto.AuthorResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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

    public AuthorDTO convertToDto() {
        AuthorDTO authorDTO = new AuthorDTO();
        authorDTO.setName(this.getName());
        authorDTO.setSurname(this.getSurname());
        authorDTO.setId(this.getId());
        authorDTO.setPatronymic(this.getPatronymic());
        authorDTO.setAuthorsGenresList(this.getAuthorsGenresList().stream().map(Genre::convertToDto).toList());
        authorDTO.setAuthorsBooksList(this.getAuthorsBooksList().stream().map(Book::convertToResponseDto).toList());
        authorDTO.setDateOfBirth(this.getDateOfBirth());

        return authorDTO;
    }

    public AuthorResponseDTO convertToResponseDTO(){
        AuthorResponseDTO authorResponseDTO=new AuthorResponseDTO();
        authorResponseDTO.setName(this.getName());
        authorResponseDTO.setSurname(this.getSurname());
        authorResponseDTO.setId(this.getId());
        authorResponseDTO.setPatronymic(this.getPatronymic());
        authorResponseDTO.setDateOfBirth(this.getDateOfBirth());
        return authorResponseDTO;
    }
}
