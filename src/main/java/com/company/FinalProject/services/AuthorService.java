package com.company.FinalProject.services;

import com.company.FinalProject.entity.Author;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    public Author create(Author author);
    public void delete(long id);
    public Author update(Author author);
    public Optional<Author> findById(long id);
    List<Author> findByFIO(String name);
    List<Author> getAll();
}
