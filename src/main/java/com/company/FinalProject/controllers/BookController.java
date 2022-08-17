package com.company.FinalProject.controllers;

import com.company.FinalProject.dto.Book.BookDTO;
import com.company.FinalProject.dto.Book.BookFullDTO;
import com.company.FinalProject.dto.Book.BookShortDTO;
import com.company.FinalProject.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public List<BookFullDTO> getAll(){
        return bookService.getAll();
    }

    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    @GetMapping("/book/{bookID}")
    public BookFullDTO getBookById(@PathVariable("bookID") long id)
    {
        return bookService.findById(id);
    }

    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    @GetMapping("/book/name/{bookName}")
    public List<BookShortDTO> getBookByName(@PathVariable("bookName") String name) {
        return bookService.getByNameContaining(name);
    }

    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    @GetMapping("/book/genre/{genreName}")
    public List<BookShortDTO> getAuthorByGenreName(@PathVariable("genreName") List<String> genreName)
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
    public BookFullDTO saveBook(@RequestBody BookDTO bookDTO){
        return bookService.create(bookDTO);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/book")
    public void updateBook(@RequestBody BookDTO bookDTO)    {
        bookService.update(bookDTO);
    }
}