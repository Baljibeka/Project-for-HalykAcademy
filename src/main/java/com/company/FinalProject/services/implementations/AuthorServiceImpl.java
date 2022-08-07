package com.company.FinalProject.services.implementations;

import com.company.FinalProject.dto.Author.AuthorDTO;
import com.company.FinalProject.dto.Author.AuthorResponseDTO;
import com.company.FinalProject.entity.Author;
import com.company.FinalProject.repo.AuthorRepository;
import com.company.FinalProject.repo.BookRepository;
import com.company.FinalProject.repo.GenreRepository;
import com.company.FinalProject.services.AuthorService;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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

    @Override
    public Optional<AuthorResponseDTO> findById(long id) {
        return authorRepo.findById(id).map(Author::convertToResponseDTO);
    }

    @Override
    public List<AuthorResponseDTO> findByFIO(String name) {
        return authorRepo.findByName(name).stream().map(Author::convertToResponseDTO).collect(Collectors.toList());
    }
    public List<AuthorResponseDTO> getAll(){
        List<Author> authors = authorRepo.findAll();
        return authors.stream()
                .map(Author::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<AuthorResponseDTO> getByGenreName(String genreName) {
        return authorRepo.findAllByGenre(genreName).stream().map(Author::convertToResponseDTO).toList();
    }
}




