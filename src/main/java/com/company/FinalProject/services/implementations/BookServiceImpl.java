package com.company.FinalProject.services.implementations;

import com.company.FinalProject.dto.Book.BookDTO;
import com.company.FinalProject.dto.Book.BookResponseDTO;
import com.company.FinalProject.entity.Book;
import com.company.FinalProject.repo.BookRepository;
import com.company.FinalProject.services.BookService;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepo;

    public BookServiceImpl(BookRepository bookRepo) {
        super();
        this.bookRepo = bookRepo;
    }

    @Override
    public BookResponseDTO create(BookResponseDTO bookResponseDTO) {
        Book book = bookResponseDTO.convertToEntity();
        Book bookCreated = bookRepo.save(book);
        return bookCreated.convertToResponseDto();
    }

    @Override
    public void delete(long id) {
        bookRepo.deleteById(id);
    }

    @Override
    public void update(BookDTO bookDTO) {
        Book book = bookDTO.convertToEntity();
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
    public Optional<BookDTO> findById(long id) {
        return bookRepo.findById(id).map(Book::convertToDto);
    }

    @Override
    public List<BookDTO> getAll() {
        return bookRepo.findAll().stream().map(Book::convertToDto).collect(Collectors.toList());
    }

    @Override
    public List<BookDTO> getByNameContaining(String name) {
        return  bookRepo.findByNameIsContainingIgnoreCase(name).stream().map(Book::convertToDto).collect(Collectors.toList());
    }

    @Override
    public List<BookResponseDTO> getByGenreName(String genreName) {
        return bookRepo.findAllByGenre(genreName).stream().map(Book::convertToResponseDto).toList();
    }
}
