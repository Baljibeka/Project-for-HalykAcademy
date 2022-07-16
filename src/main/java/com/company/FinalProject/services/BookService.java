package com.company.FinalProject.services;

import com.company.FinalProject.dto.BookDTO;

import java.util.List;
import java.util.Optional;

public interface BookService {
    public BookDTO createBook(BookDTO bookDTO);
    public void deleteBook(long id);
    public BookDTO updateBook(BookDTO bookDTO);
    public Optional<BookDTO> findByIdBook(long id);
    List<BookDTO> findByName(String name);
}
