package com.company.FinalProject.dto;

import com.company.FinalProject.entity.Book;

import java.util.List;

public class PublisherDTO {
    private long id;

    private String name;

    private List<Book> publishedBooks;

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

    public List<Book> getPublishedBooks() {
        return publishedBooks;
    }

    public void setPublishedBooks(List<Book> publishedBooks) {
        this.publishedBooks = publishedBooks;
    }
}
