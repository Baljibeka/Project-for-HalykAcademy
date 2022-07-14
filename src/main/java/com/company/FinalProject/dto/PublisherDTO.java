package com.company.FinalProject.dto;

import java.util.List;

public class PublisherDTO {
    //id, название, список изданных книг
    private long id;
    private String name;
    private List<BookDTO> books;

    public PublisherDTO(){}

    public PublisherDTO(long id, String name, List<BookDTO> books) {
        this.id = id;
        this.name = name;
        this.books = books;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<BookDTO> getBooks() {
        return books;
    }

    public void setBooks(List<BookDTO> books) {
        this.books = books;
    }
}
