package com.company.FinalProject.services;

import com.company.FinalProject.dto.Book.BookDTO;
import com.company.FinalProject.dto.Book.BookResponseDTO;

import java.util.List;
import java.util.Optional;

public interface BookService {
    public BookResponseDTO create(BookDTO bookDTO);
    public void delete(long id);
    public void update(BookDTO bookDTO);
    public Optional<BookResponseDTO> findById(long id);
    public List<BookResponseDTO> getAll();
    List<BookResponseDTO> getByNameContaining(String name);
    List<BookResponseDTO> getByGenreName(String genreName);
}
