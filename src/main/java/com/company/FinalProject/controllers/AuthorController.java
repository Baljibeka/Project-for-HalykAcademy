package com.company.FinalProject.controllers;

import com.company.FinalProject.dto.AuthorDTO;
import com.company.FinalProject.entity.Author;
import com.company.FinalProject.services.AuthorService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/authors")
public class AuthorController {
    @Autowired
    private AuthorService authorService;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("/author")
    public List<AuthorDTO> getAll() {
        List<Author> authors = authorService.getAll();
        return authors.stream()
                .map(this::convertAuthorToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/author/{authorID}")
    private Optional<AuthorDTO> getAuthorById(@PathVariable("authorID") long id) {
        Optional<Author> authors = authorService.findById(id);
        return authors.map(this::convertAuthorToDto);
    }

    @GetMapping("/author/{authorName}")
    private List<AuthorDTO> getAuthorByName(@PathVariable("authorName") String name) {
        List<Author> authors = authorService.findByFIO(name);
        return authors
                .stream()
                .map(this::convertAuthorToDto)
                .collect(Collectors.toList());
    }

    @DeleteMapping("/author/{authorID}")
    private void deleteAuthorById(@PathVariable("authorID") long id) {
        authorService.delete(id);
    }

    @PostMapping("/author")
    private AuthorDTO saveBook(@RequestBody AuthorDTO authorDTO) {
        Author author = convertToEntity(authorDTO);
        Author authorCreated = authorService.create(author);
        return convertAuthorToDto(authorCreated);
    }

    @PutMapping("/author/{authorID}")
    private void updateBook(@RequestBody AuthorDTO authorDTO, @PathVariable("authorID") long id) {
        if (!Objects.equals(id, authorDTO.getId())) {
            throw new IllegalArgumentException("IDs don't match");
        }
        Author author = convertToEntity(authorDTO);
        authorService.update(author);
    }

    private AuthorDTO convertAuthorToDto(Author author) {
        AuthorDTO authorDTO = modelMapper.map(author, AuthorDTO.class);
        authorDTO.setName(author.getName());
        authorDTO.setSurname(author.getSurname());
        authorDTO.setId(author.getId());
        authorDTO.setAuthorsBooksList(author.getAuthorsBooksList());
        authorDTO.setDateOfBirth(author.getDateOfBirth());

        return authorDTO;
    }

    public Author convertToEntity(AuthorDTO authorDTO) {
        Author author = new Author();
        author.setName(authorDTO.getName());
        author.setId(authorDTO.getId());
        author.setAuthorsBooksList(authorDTO.getAuthorsBooksList());
        author.setPatronymic(authorDTO.getPatronymic());
        author.setSurname(authorDTO.getSurname());
        author.setDateOfBirth(authorDTO.getDateOfBirth());

        return author;
    }
}