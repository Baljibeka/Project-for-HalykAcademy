package com.company.FinalProject.dto;

import com.company.FinalProject.entity.Author;
import com.company.FinalProject.entity.Book;
import com.company.FinalProject.entity.Genre;
import com.company.FinalProject.entity.Publisher;

import java.time.LocalDate;
import java.util.List;

public class BookDTO {
    private long id;
    private int price;
    private List<AuthorDTO> authorList;
    private PublisherDTO publisher;
    private String name;
    private int numberOfPages;
    private LocalDate yearOfIssue;
    private List<GenreDTO> genreList;

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

    public List<AuthorDTO> getAuthorList() {
        return authorList;
    }

    public void setAuthorList(List<AuthorDTO> authorList) {
        this.authorList = authorList;
    }

    public PublisherDTO getPublisher() {
        return publisher;
    }

    public void setPublisher(PublisherDTO publisher) {
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

    public List<GenreDTO> getGenreList() {
        return genreList;
    }

    public void setGenreList(List<GenreDTO> genreList) {
        this.genreList = genreList;
    }

    public Book convertToEntity() {
        Book book = new Book();
        book.setName(this.getName());
        book.setPublisher(this.getPublisher().convertToEntity());
        book.setPrice(this.getPrice());
        book.setYearOfIssue(this.getYearOfIssue());
        book.setId(this.getId());
        book.setBooksGenresList(this.getGenreList().stream().map(GenreDTO::convertToEntity).toList());
        book.setAuthorList(this.getAuthorList().stream().map(AuthorDTO::convertToEntity).toList());
        book.setNumberOfPages(this.getNumberOfPages());
        return book;
    }
}
