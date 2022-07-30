package com.company.FinalProject.services;

import com.company.FinalProject.dto.BookDTO;
import com.company.FinalProject.dto.BookResponseDTO;

import java.util.List;
import java.util.Optional;

public interface BookService {
    public BookResponseDTO create(BookResponseDTO bookResponseDTO);
    public void delete(long id);
    public void update(BookDTO bookDTO);
    public Optional<BookDTO> findById(long id);
    public List<BookDTO> getAll();
    List<BookDTO> getByNameContaining(String name);
    List<BookResponseDTO> getByGenreName(String genreName);
}
