package com.company.FinalProject.services.implementations;

import com.company.FinalProject.dto.Book.BookDTO;
import com.company.FinalProject.dto.Book.BookResponseDTO;
import com.company.FinalProject.entity.Book;
import com.company.FinalProject.repo.AuthorRepository;
import com.company.FinalProject.repo.BookRepository;
import com.company.FinalProject.repo.GenreRepository;
import com.company.FinalProject.repo.PublisherRepository;
import com.company.FinalProject.services.BookService;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepo;
    private final AuthorRepository authorRepo;
    private final GenreRepository genreRepo;
    private final PublisherRepository publisherRepo;

    public BookServiceImpl(BookRepository bookRepo, AuthorRepository authorRepo, GenreRepository genreRepo, PublisherRepository publisherRepo) {
        this.bookRepo = bookRepo;
        this.authorRepo=authorRepo;
        this.genreRepo=genreRepo;
        this.publisherRepo=publisherRepo;
    }

    @Override
    public BookResponseDTO create(BookDTO bookDTO) {
        val authors=authorRepo.findAllById(bookDTO.getAuthorList());
        val genres=genreRepo.findAllById(bookDTO.getGenreList());
        val publishers=publisherRepo.findById(bookDTO.getPublisherId()).orElseThrow();
        Book book = bookDTO.convertToEntity(publishers, genres, authors);
        Book bookCreated = bookRepo.save(book);
        return bookCreated.convertToResponseDto();
    }

    @Override
    public void delete(long id) {
        bookRepo.deleteById(id);
    }

    @Override
    public void update(BookDTO bookDTO) {
        val authors=authorRepo.findAllById(bookDTO.getAuthorList());
        val genres=genreRepo.findAllById(bookDTO.getGenreList());
        val publishers=publisherRepo.findById(bookDTO.getPublisherId()).orElseThrow();
        Book book = bookDTO.convertToEntity(publishers, genres, authors);
        bookRepo.save(book);
    }

    @Override
    public Optional<BookResponseDTO> findById(long id) {
        return bookRepo.findById(id).map(Book::convertToResponseDto);
    }

    @Override
    public List<BookResponseDTO> getAll() {
        return bookRepo.findAll().stream().map(Book::convertToResponseDto).collect(Collectors.toList());
    }

    @Override
    public List<BookResponseDTO> getByNameContaining(String name) {
        return  bookRepo.findByNameIsContainingIgnoreCase(name).stream().map(Book::convertToResponseDto).collect(Collectors.toList());
    }

    @Override
    public List<BookResponseDTO> getByGenreName(String genreName) {
        return bookRepo.findAllByGenre(genreName).stream().map(Book::convertToResponseDto).toList();
    }
}
