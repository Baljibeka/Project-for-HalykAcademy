package com.company.FinalProject.services.implementations;

import com.company.FinalProject.dto.Book.BookDTO;
import com.company.FinalProject.dto.Book.BookFullDTO;
import com.company.FinalProject.dto.Book.BookShortDTO;
import com.company.FinalProject.entity.Book;
import com.company.FinalProject.exception.NotFoundException;
import com.company.FinalProject.repo.AuthorRepository;
import com.company.FinalProject.repo.BookRepository;
import com.company.FinalProject.repo.GenreRepository;
import com.company.FinalProject.repo.PublisherRepository;
import com.company.FinalProject.services.BookService;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.List;
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
    public BookFullDTO create(BookDTO bookDTO) {
        val authors=authorRepo.findAllById(bookDTO.getAuthorList());
        val genres=genreRepo.findAllById(bookDTO.getGenreList());
        val publisher=publisherRepo.findById(bookDTO.getPublisherId()).orElseThrow();
            if(publisher.getIsBlocked()) throw new NotFoundException("Publisher is deleted");
        Book book = bookDTO.convertToEntity(publisher, genres, authors);
        Book bookCreated = bookRepo.save(book);
        return bookCreated.convertToResponseDto();
    }

    @Override
    public void delete(long id) {
        Book book = bookRepo.findById(id).orElseThrow(()->new NotFoundException("there is no such book"));
        bookRepo.save(new Book(
                book.getId(),
                book.getPrice(),
                book.getAuthorList(),
                book.getPublisher(),
                book.getName(),
                book.getNumberOfPages(),
                book.getYearOfIssue(),
                book.getBooksGenresList(),
                false
        ));
    }

    @Override
    public void update(BookDTO bookDTO) {
        val authors=authorRepo.findAllById(bookDTO.getAuthorList());
        val genres=genreRepo.findAllById(bookDTO.getGenreList());
        val publisher=publisherRepo.findById(bookDTO.getPublisherId()).orElseThrow();
        if(publisher.getIsBlocked()) throw new NotFoundException("Publisher is deleted");
        Book book = bookDTO.convertToEntity(publisher, genres, authors);
        bookRepo.save(book);
    }

    @Override
    public BookFullDTO findById(long id) {
        Book book =bookRepo.findById(id).orElseThrow(()-> new NotFoundException("There is no such book"));
        return book.convertToResponseDto();
    }

    @Override
    public List<BookFullDTO> getAll() {
        return bookRepo.findAll().stream().map(Book::convertToResponseDto).collect(Collectors.toList());
    }

    @Override
    public List<BookShortDTO> getByNameContaining(String name) {
        return  bookRepo.findByNameIsContainingIgnoreCase(name).stream().map(Book::convertToShortDTO).collect(Collectors.toList());
    }

    @Override
    public List<BookShortDTO> getByGenreName(List<String> genreName) {
        return bookRepo.findAllByGenre(genreName).stream().map(Book::convertToShortDTO).toList();
    }
}
