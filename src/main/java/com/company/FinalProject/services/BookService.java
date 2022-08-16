package com.company.FinalProject.services;

import com.company.FinalProject.dto.Book.BookDTO;
import com.company.FinalProject.dto.Book.BookFullDTO;
import com.company.FinalProject.dto.Book.BookShortDTO;

import java.util.List;

public interface BookService {
    public BookFullDTO create(BookDTO bookDTO);
    public void delete(long id);
    public void update(BookDTO bookDTO);
    public BookFullDTO findById(long id);
    public List<BookFullDTO> getAll();
    List<BookShortDTO> getByNameContaining(String name);
    List<BookShortDTO> getByGenreName(List<String> genreList);
}
