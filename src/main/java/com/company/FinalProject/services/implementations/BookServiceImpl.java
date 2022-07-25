package com.company.FinalProject.services.implementations;

import com.company.FinalProject.entity.Book;
import com.company.FinalProject.repo.BookRepository;
import com.company.FinalProject.services.BookService;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepo;

    public BookServiceImpl(BookRepository bookRepo) {
        super();
        this.bookRepo = bookRepo;
    }

    @Override
    public Book create(Book book) {
        return bookRepo.save(book);
    }

    @Override
    public void delete(long id) {
        bookRepo.deleteById(id);
    }

    @Override
    public void update(Book book) {
        Book existingBook = null;
        try {
            existingBook = bookRepo.findById(book.getId()).orElseThrow(ChangeSetPersister.NotFoundException::new);
            existingBook.setName(book.getName());
            existingBook.setNumberOfPages(book.getNumberOfPages());
            existingBook.setYearOfIssue(book.getYearOfIssue());
            existingBook.setPublisher(book.getPublisher());
            existingBook.setAuthorList(book.getAuthorList());
            existingBook.setPrice(book.getPrice());
            bookRepo.save(book);
        } catch (ChangeSetPersister.NotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Book> findById(long id) {
        return bookRepo.findById(id);
    }

    @Override
    public List<Book> getAll() {
        return bookRepo.findAll();
    }

    @Override
    public List<Book> getByNameContaining(String name) {
        return  bookRepo.findByNameIsContainingIgnoreCase(name);
    }
}
