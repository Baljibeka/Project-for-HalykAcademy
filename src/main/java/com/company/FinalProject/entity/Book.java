package com.company.FinalProject.entity;

import com.company.FinalProject.dto.AuthorDTO;
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
    private long id;
    @Column(name="price")
    private int price;
    @ManyToMany(mappedBy = "authorsBooksList")
    private List<Author> authorList;
    @Column(name="publisher")
    private String publisher;
    @Column(name="name")
    private String name;
    @Column(name="number_of_pages")
    private int numberOfPages;
    @Column(name="year_of_issue")
    private LocalDate yearOfIssue;

    public Book() {}

    public Book(int price,
                List<Author> authorList,
                String publisher,
                String name,
                int numberOfPages,
                LocalDate yearOfIssue) {
        this.price = price;
        this.authorList = authorList;
        this.publisher = publisher;
        this.name = name;
        this.numberOfPages = numberOfPages;
        this.yearOfIssue = yearOfIssue;
    }

    public Book(long id,
                int price,
                List<Author> authorList,
                String publisher,
                String name,
                int numberOfPages,
                LocalDate yearOfIssue) {
        this.id = id;
        this.price = price;
        this.authorList = authorList;
        this.publisher = publisher;
        this.name = name;
        this.numberOfPages = numberOfPages;
        this.yearOfIssue = yearOfIssue;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
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
}
