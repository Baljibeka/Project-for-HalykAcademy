package com.company.FinalProject.dto;

import com.company.FinalProject.entity.Book;
import com.company.FinalProject.entity.Publisher;

import java.util.List;

public class PublisherDTO {
    private long id;

    private String name;

    private List<BookDTO> publishedBooks;

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

    public List<BookDTO> getPublishedBooks() {
        return publishedBooks;
    }

    public void setPublishedBooks(List<BookDTO> publishedBooks) {
        this.publishedBooks = publishedBooks;
    }

    public Publisher convertToEntity() {
        Publisher publisher = new Publisher();
        publisher.setName(this.getName());
        publisher.setId(this.getId());
        publisher.setPublishedBooksList(this.getPublishedBooks().stream().map(BookDTO::convertToEntity).toList());
        return publisher;
    }
}
