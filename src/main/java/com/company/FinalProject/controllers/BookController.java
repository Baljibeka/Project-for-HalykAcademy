package com.company.FinalProject.controllers;

import com.company.FinalProject.dto.Book.BookDTO;
import com.company.FinalProject.dto.Book.BookResponseDTO;
import com.company.FinalProject.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
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
    @GetMapping("/book")
    public List<BookDTO> getAll(){
        return bookService.getAll();
    }
    @GetMapping("/book/{bookID}")
    private Optional<BookDTO> getBookById(@PathVariable("bookID") long id)
    {
        return bookService.findById(id);
    }

    @GetMapping("/book/name/{bookName}")
    private List<BookDTO> getBookByName(@PathVariable("bookName") String name) {
        return bookService.getByNameContaining(name);
    }

    @GetMapping("/book/genre/{genreName}")
    private List<BookResponseDTO> getAuthorByGenreName(@PathVariable("genreName") String genreName)
    {
        return bookService.getByGenreName(genreName);

    }

    @DeleteMapping("/book/{bookID}")
    private void deleteBookById(@PathVariable("bookID") long id)
    {
        bookService.delete(id);
    }

    @PostMapping("/book")
    private BookResponseDTO saveBook(@RequestBody BookResponseDTO bookResponseDTO){
        return bookService.create(bookResponseDTO);
    }
    @PutMapping("/book/{bookID}")
    private void updateBook(@RequestBody BookDTO bookDTO,@PathVariable("bookID") long id)    {
        if(!Objects.equals(id, bookDTO.getId())){
            throw new IllegalArgumentException("IDs don't match");
        }
        bookService.update(bookDTO);
    }
}