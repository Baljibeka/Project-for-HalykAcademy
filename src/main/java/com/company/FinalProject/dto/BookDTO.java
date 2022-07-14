package com.company.FinalProject.dto;

import java.util.List;

public class BookDTO {
    private long id;
    private List<AuthorDTO> authors;
    private PublisherDTO publisher;
    private String title;
    private long number_of_pages;
    private String creationDate;

    public BookDTO(){}

    public BookDTO(long id, List<AuthorDTO> authors, PublisherDTO publisher, String title, long number_of_pages, String creationDate) {
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

    public List<AuthorDTO> getAuthors() {
        return authors;
    }

    public void setAuthors(List<AuthorDTO> authors) {
        this.authors = authors;
    }

    public PublisherDTO getPublisher() {
        return publisher;
    }

    public void setPublisher(PublisherDTO publisher) {
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
