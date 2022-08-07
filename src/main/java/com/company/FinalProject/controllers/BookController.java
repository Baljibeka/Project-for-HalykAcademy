package com.company.FinalProject.controllers;

import com.company.FinalProject.dto.Book.BookDTO;
import com.company.FinalProject.dto.Book.BookResponseDTO;
import com.company.FinalProject.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/books")
public class BookController {
    @Autowired
    private BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }
    @GetMapping
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    public List<BookDTO> getAll(){
        return bookService.getAll();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/book/{bookID}")
    public Optional<BookDTO> getBookById(@PathVariable("bookID") long id)
    {
        return bookService.findById(id);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/book/name/{bookName}")
    public List<BookDTO> getBookByName(@PathVariable("bookName") String name) {
        return bookService.getByNameContaining(name);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/book/genre/{genreName}")
    public List<BookResponseDTO> getAuthorByGenreName(@PathVariable("genreName") String genreName)
    {
        return bookService.getByGenreName(genreName);

    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/book/{bookID}")
    public void deleteBookById(@PathVariable("bookID") long id)
    {
        bookService.delete(id);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/book")
    public BookResponseDTO saveBook(@RequestBody BookResponseDTO bookResponseDTO){
        return bookService.create(bookResponseDTO);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/book")
    public void updateBook(@RequestBody BookDTO bookDTO)    {
        bookService.update(bookDTO);
    }
}