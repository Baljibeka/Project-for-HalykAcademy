package com.company.FinalProject.services.implementations;

import com.company.FinalProject.dto.Author.AuthorDTO;
import com.company.FinalProject.dto.Author.AuthorResponseDTO;
import com.company.FinalProject.dto.Author.AuthorShortDTO;
import com.company.FinalProject.entity.Author;
import com.company.FinalProject.entity.Book;
import com.company.FinalProject.entity.Genre;
import com.company.FinalProject.repo.AuthorRepository;
import com.company.FinalProject.repo.BookRepository;
import com.company.FinalProject.services.AuthorService;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepo;
    private final BookRepository bookRepo;

    public AuthorServiceImpl(AuthorRepository authorRepo, BookRepository bookRepo) {
        this.authorRepo = authorRepo;
        this.bookRepo = bookRepo;
    }

    public void create(AuthorDTO authorDTO) {
        val books = bookRepo.findAllById(authorDTO.getAuthorsBooksList());
        Author author = authorDTO.convertToEntity(books);
        authorRepo.save(author).convertToResponseDTO();
    }

    public void delete(long id) {
        authorRepo.deleteById(id);
    }

    public void update(AuthorDTO authorDTO) {
        val books = bookRepo.findAllById(authorDTO.getAuthorsBooksList());
        Author author = authorDTO.convertToEntity(books);
        authorRepo.save(author);
    }
    public void addingGenres(Author author){
        for(Book book:author.getAuthorsBooksList()){
            for(Genre genre:book.getBooksGenresList()){
                if(!author.getGenresList().contains(genre)){
                    author.getGenresList().add(genre);
                }
            }
        }
    }
    @Override
    public AuthorResponseDTO findById(long id) {
        Author author = authorRepo.findById(id).orElseThrow();
        addingGenres(author);
        return author.convertToResponseDTO();
    }

    @Override
    public List<AuthorResponseDTO> findByFIO(String name) {
        String fio="%" + name + "%";
        List<Author> authors = authorRepo.findByFIO(fio, fio, fio);
        for(Author author:authors) {
            addingGenres(author);
        }
        return authors.stream().map(Author::convertToResponseDTO).collect(Collectors.toList());
    }

    @Override
    public List<AuthorResponseDTO> getAll(){
        List<Author> authors = authorRepo.findAll();
        for(Author author:authors){
            addingGenres(author);
        }
        return authors.stream()
                .map(Author::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Set<AuthorShortDTO> getByGenreName(List<String> genreName) {
        return authorRepo.findAllByGenresListIsContainingIgnoreCase(genreName).stream().map(Author::convertToShortDTO).collect(Collectors.toSet());
    }
}




