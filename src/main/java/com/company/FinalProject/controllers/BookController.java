package com.company.FinalProject.controllers;

import com.company.FinalProject.dto.AuthorDTO;
import com.company.FinalProject.dto.BookDTO;
import com.company.FinalProject.entity.Author;
import com.company.FinalProject.entity.Book;
import com.company.FinalProject.services.BookService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/books")
public class BookController {
    @Autowired
    private BookService bookService;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }
    @GetMapping("/book")
    public List<BookDTO> getAll(){
        List<Book> books=bookService.getAll();
        return books.stream()
                .map(this::convertBookToDto)
                .collect(Collectors.toList());
    }
    @GetMapping("/book/{bookID}")
    private Optional<BookDTO> getBookById(@PathVariable("bookID") long id)
    {
        Optional<Book> books=bookService.findById(id);
        return books.map(this::convertBookToDto);
    }

    @GetMapping("/book/{bookName}")
    private List<BookDTO> getBookByName(@PathVariable("bookName") String name) {
        List<Book> books = bookService.getByNameContaining(name);
        return books
                .stream()
                .map(this::convertBookToDto)
                .collect(Collectors.toList());
    }

    @DeleteMapping("/book/{bookID}")
    private void deleteBookById(@PathVariable("bookID") long id)
    {
        bookService.delete(id);
    }

    @PostMapping("/book")
    private BookDTO saveBook(@RequestBody BookDTO bookDTO){
        Book book = convertToEntity(bookDTO);
        Book bookCreated = bookService.create(book);
        return convertBookToDto(bookCreated);
    }
    @PutMapping("/book/{bookID}")
    private void updateBook(@RequestBody BookDTO bookDTO,@PathVariable("bookID") long id)    {
        if(!Objects.equals(id, bookDTO.getId())){
            throw new IllegalArgumentException("IDs don't match");
        }
        Book book = convertToEntity(bookDTO);
        bookService.update(book);
    }

    private BookDTO convertBookToDto(Book book) {
        BookDTO bookDto = modelMapper.map(book, BookDTO.class);
        bookDto.setName(book.getName());
        bookDto.setAuthorList(book.getAuthorList());
        bookDto.setId(book.getId());
        bookDto.setPrice(book.getPrice());
        bookDto.setPublisher(book.getPublisher());
        bookDto.setNumberOfPages(book.getNumberOfPages());
        bookDto.setYearOfIssue(book.getYearOfIssue());

        return bookDto;
    }
    public Book convertToEntity(BookDTO bookDTO) {
        Book book = new Book();
        book.setName(bookDTO.getName());
        book.setPublisher(bookDTO.getPublisher());
        book.setPrice(bookDTO.getPrice());
        book.setYearOfIssue(bookDTO.getYearOfIssue());
        book.setId(bookDTO.getId());
        book.setAuthorList(bookDTO.getAuthorList());
        book.setNumberOfPages(bookDTO.getNumberOfPages());

        return book;
    }
}