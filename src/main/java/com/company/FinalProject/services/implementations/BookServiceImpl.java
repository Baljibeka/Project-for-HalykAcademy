package com.company.FinalProject.services.implementations;

import com.company.FinalProject.dto.BookDTO;
import com.company.FinalProject.repo.BookRepository;
import com.company.FinalProject.services.BookService;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.Optional;

public class BookServiceImpl implements BookService {
    private final BookRepository bookRepo;

    public BookServiceImpl(BookRepository bookRepo) {
        this.bookRepo = bookRepo;
    }

    @Override
    public BookDTO createBook(BookDTO bookDTO) {
        return bookRepo.save(bookDTO);
    }

    @Override
    public void deleteBook(long id) {
        bookRepo.deleteById(id);
    }

    @Override
    public BookDTO updateBook(BookDTO bookDTO) {
        BookDTO existingBook = null;
        try {
            existingBook = bookRepo.findById(bookDTO.getId()).orElseThrow(ChangeSetPersister.NotFoundException::new);
            existingBook.setTitle(bookDTO.getTitle());
            existingBook.setNumber_of_pages(bookDTO.getNumber_of_pages());
            existingBook.setCreationDate(bookDTO.getCreationDate());
            existingBook.setPublisher(bookDTO.getPublisher());
            existingBook.setAuthors(bookDTO.getAuthors());
            bookRepo.save(existingBook);
        } catch (ChangeSetPersister.NotFoundException e) {
            e.printStackTrace();
        }
        return existingBook;
    }

    @Override
    public Optional<BookDTO> findByIdBook(long id) {
        return bookRepo.findById(id);
    }
}
