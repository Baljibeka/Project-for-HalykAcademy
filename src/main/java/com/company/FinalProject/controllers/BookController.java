package com.company.FinalProject.controllers;

import com.company.FinalProject.dto.BookDTO;
import com.company.FinalProject.services.BookService;

import java.util.Optional;

public class BookController {
    private BookService bookService;

    public BookController(BookService bookService){
        this.bookService=bookService;
    }

    public BookDTO createBook(BookDTO bookDTO){
        return bookService.createBook(bookDTO);
    }

    public void deleteBook(long id){
        bookService.deleteBook(id);
    }

    public BookDTO updateBook(BookDTO bookDTO){
        return bookService.updateBook(bookDTO);
    }

    public Optional<BookDTO> findByIdBook(long id){
        return bookService.findByIdBook(id);
    }
}
