package com.company.FinalProject.services.implementations;

import com.company.FinalProject.dto.Author.AuthorDTO;
import com.company.FinalProject.dto.Author.AuthorResponseDTO;
import com.company.FinalProject.entity.Author;
import com.company.FinalProject.repo.AuthorRepository;
import com.company.FinalProject.repo.BookRepository;
import com.company.FinalProject.repo.GenreRepository;
import com.company.FinalProject.services.AuthorService;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.net.http.HttpResponse;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepo;
    private final BookRepository bookRepo;
    private final GenreRepository genreRepo;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepo, BookRepository bookRepo, GenreRepository genreRepo) {
        this.authorRepo = authorRepo;
        this.bookRepo=bookRepo;
        this.genreRepo=genreRepo;
    }


    public AuthorResponseDTO create(AuthorDTO authorDTO) {
        val books = bookRepo.findAllById(authorDTO.getAuthorsBooksList());
        val genres = genreRepo.findAllById(authorDTO.getAuthorsGenresList());
        Author author = authorDTO.convertToEntity(genres, books);
        return authorRepo.save(author).convertToResponseDTO();

    }

    public void delete(long id) {
        authorRepo.deleteById(id);
    }

    public void update(AuthorDTO authorDTO) {
        val books = bookRepo.findAllById(authorDTO.getAuthorsBooksList());
        val genres = genreRepo.findAllById(authorDTO.getAuthorsGenresList());
        Author author = authorDTO.convertToEntity(genres, books);
        Author existingAuthor = null;
        try {
            existingAuthor = authorRepo.findById(author.getId()).orElseThrow(ChangeSetPersister.NotFoundException::new);
            existingAuthor.setName(author.getName());
            existingAuthor.setSurname(author.getSurname());
            existingAuthor.setPatronymic(author.getPatronymic());
            existingAuthor.setDateOfBirth(author.getDateOfBirth());
            existingAuthor.setAuthorsBooksList(author.getAuthorsBooksList());
            existingAuthor.setAuthorsGenresList(author.getAuthorsGenresList());
            authorRepo.save(author);
        } catch (ChangeSetPersister.NotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<AuthorDTO> findById(long id) {
        return authorRepo.findById(id).map(Author::convertToDto);
    }

    @Override
    public List<AuthorDTO> findByFIO(String name) {
        return authorRepo.findByName(name).stream().map(Author::convertToDto).collect(Collectors.toList());
    }
    public List<AuthorDTO> getAll(){
        List<Author> authors = authorRepo.findAll();
        return authors.stream()
                .map(Author::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<AuthorResponseDTO> getByGenreName(String genreName) {
        return authorRepo.findAllByGenre(genreName).stream().map(Author::convertToResponseDTO).toList();
    }
}




