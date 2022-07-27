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
                .map(item->item.convertToDto(true))
                .collect(Collectors.toList());
    }

    @GetMapping("/author/{authorID}")
    private Optional<AuthorDTO> getAuthorById(@PathVariable("authorID") long id) {
        Optional<Author> authors = authorService.findById(id);
        return authors.map(it->it.convertToDto(true));
    }

    @GetMapping("/author/{authorName}")
    private List<AuthorDTO> getAuthorByName(@PathVariable("authorName") String name) {
        List<Author> authors = authorService.findByFIO(name);
        return authors
                .stream()
                .map(it->it.convertToDto(true))
                .collect(Collectors.toList());
    }

    @DeleteMapping("/author/{authorID}")
    private void deleteAuthorById(@PathVariable("authorID") long id) {
        authorService.delete(id);
    }

    @PostMapping("/author")
    private AuthorDTO save(@RequestBody AuthorDTO authorDTO) {
        Author author = authorDTO.convertToEntity();
        Author authorCreated = authorService.create(author);
        return authorCreated.convertToDto(true);
    }

    @PutMapping("/author/{authorID}")
    private void updateBook(@RequestBody AuthorDTO authorDTO, @PathVariable("authorID") long id) {
        if (!Objects.equals(id, authorDTO.getId())) {
            throw new IllegalArgumentException("IDs don't match");
        }
        Author author = authorDTO.convertToEntity();
        authorService.update(author);
    }

}