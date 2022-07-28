package com.company.FinalProject.controllers;

import com.company.FinalProject.dto.AuthorDTO;
import com.company.FinalProject.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/authors")
public class AuthorController {
    @Autowired
    private AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("/author")
    public List<AuthorDTO> getAll() {
        return authorService.getAll();
    }

    @GetMapping("/author/{authorID}")
    private Optional<AuthorDTO> getAuthorById(@PathVariable("authorID") long id) {
        return authorService.findById(id);
    }

    @GetMapping("/author/{authorName}")
    private List<AuthorDTO> getAuthorByName(@PathVariable("authorName") String name) {
        return authorService.findByFIO(name);
    }

    @DeleteMapping("/author/{authorID}")
    private void deleteAuthorById(@PathVariable("authorID") long id) {
        authorService.delete(id);
    }

    @PostMapping("/author")
    private AuthorDTO save(@RequestBody AuthorDTO authorDTO) {
        return authorService.create(authorDTO);
    }

    @PutMapping("/author/{authorID}")
    private void updateBook(@RequestBody AuthorDTO authorDTO, @PathVariable("authorID") long id) {
        if (!Objects.equals(id, authorDTO.getId())) {
            throw new IllegalArgumentException("IDs don't match");
        }
        authorService.update(authorDTO);
    }

}