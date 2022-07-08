package com.company.FinalProject.DTO;

import com.company.FinalProject.Entity.Author;
import com.company.FinalProject.Entity.Publisher;

import java.util.List;

public class BookDTO {
    private long id;
    private List<Author> authors;
    private Publisher publisher;
    private String title;
    private long number_of_pages;
    private String creationDate;

    public BookDTO(){}

    public BookDTO(long id, List<Author> authors, Publisher publisher, String title, long number_of_pages, String creationDate) {
        this.id = id;
        this.authors = authors;
        this.publisher = publisher;
        this.title = title;
        this.number_of_pages = number_of_pages;
        this.creationDate = creationDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getNumber_of_pages() {
        return number_of_pages;
    }

    public void setNumber_of_pages(long number_of_pages) {
        this.number_of_pages = number_of_pages;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }
}
