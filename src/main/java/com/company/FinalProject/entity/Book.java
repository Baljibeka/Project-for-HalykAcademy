package com.company.FinalProject.entity;

import com.company.FinalProject.dto.AuthorDTO;
import com.company.FinalProject.dto.BookDTO;
import com.company.FinalProject.dto.PublisherDTO;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
@Entity
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
    public Book() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Genre> getBooksGenresList() {
        return booksGenresList;
    }

    public void setBooksGenresList(List<Genre> booksGenresList) {
        this.booksGenresList = booksGenresList;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public List<Author> getAuthorList() {
        return authorList;
    }

    public void setAuthorList(List<Author> authorList) {
        this.authorList = authorList;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public LocalDate getYearOfIssue() {
        return yearOfIssue;
    }

    public void setYearOfIssue(LocalDate yearOfIssue) {
        this.yearOfIssue = yearOfIssue;
    }

    public BookDTO convertToDto() {
        BookDTO bookDto = new BookDTO();
        bookDto.setName(this.getName());
        bookDto.setAuthorList(this.getAuthorList().stream().map(item->item.convertToDto(false)).toList());
        bookDto.setId(this.getId());
        bookDto.setPrice(this.getPrice());
        bookDto.setPublisher(this.getPublisher().convertToDto(false));
        bookDto.setNumberOfPages(this.getNumberOfPages());
        bookDto.setYearOfIssue(this.getYearOfIssue());
        bookDto.setGenreList(this.getBooksGenresList().stream().map(Genre::convertToDto).toList());
        return bookDto;
    }
}
