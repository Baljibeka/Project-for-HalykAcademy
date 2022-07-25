package com.company.FinalProject.services;

import com.company.FinalProject.entity.Book;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface BookService {
    public Book create(Book book);
    public void delete(long id);
    public void update(Book book);
    public Optional<Book> findById(long id);
    public List<Book> getAll();
    List<Book> getByNameContaining(String name);
}
