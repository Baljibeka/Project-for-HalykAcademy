package com.company.FinalProject.entity;

import com.company.FinalProject.dto.*;
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
@Table(name = "BOOK")
public class Book {
    @Id
    @SequenceGenerator(
            name="book_sequence",
            sequenceName="book_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "book_sequence"
    )
    @Column(name="book_id")
    private long id;
    @Column(name="price")
    private int price;
    @ManyToMany
    @JoinTable(
            name = "authors_book",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id"))
    private List<Author> authorList;
    @ManyToOne
    @JoinColumn(name = "publisher_id")
    private Publisher publisher;
    @Column(name="name")
    private String name;
    @Column(name="number_of_pages")
    private int numberOfPages;
    @Column(name="year_of_issue")
    private LocalDate yearOfIssue;
    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "book_genre",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id"))
    private List<Genre> booksGenresList;

    public BookDTO convertToDto() {
        BookDTO bookDto = new BookDTO();
        bookDto.setName(this.getName());
        bookDto.setAuthorList(this.getAuthorList().stream().map(Author::convertToResponseDTO).toList());
        bookDto.setId(this.getId());
        bookDto.setPrice(this.getPrice());
        bookDto.setPublisher(this.getPublisher().convertToResponseDto());
        bookDto.setNumberOfPages(this.getNumberOfPages());
        bookDto.setYearOfIssue(this.getYearOfIssue());
        bookDto.setGenreList(this.getBooksGenresList().stream().map(Genre::convertToDto).toList());
        return bookDto;
    }

    public BookResponseDTO convertToResponseDto() {
        BookResponseDTO bookResponseDTO = new BookResponseDTO();
        bookResponseDTO.setName(this.getName());
        bookResponseDTO.setId(this.getId());
        bookResponseDTO.setPrice(this.getPrice());
        bookResponseDTO.setPublisher(this.getPublisher().convertToResponseDto());
        bookResponseDTO.setNumberOfPages(this.getNumberOfPages());
        bookResponseDTO.setYearOfIssue(this.getYearOfIssue());
        return bookResponseDTO;
    }

}
