package com.company.FinalProject.controllers;

import com.company.FinalProject.dto.Author.AuthorDTO;
import com.company.FinalProject.dto.Author.AuthorResponseDTO;
import com.company.FinalProject.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    @GetMapping
    public List<AuthorResponseDTO> getAll() {
        return authorService.getAll();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/author/{authorID}")
    public Optional<AuthorResponseDTO> getAuthorById(@PathVariable("authorID") long id) {
        return authorService.findById(id);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/author/name/{authorName}")
    public List<AuthorResponseDTO> getAuthorByName(@PathVariable("authorName") String name) {
        return authorService.findByFIO(name);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/author/genre/{genreName}")
    public List<AuthorResponseDTO> getAuthorByGenreName(@PathVariable("genreName") String genreName)
    {
        return authorService.getByGenreName(genreName);

    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/author/{authorID}")
    public void deleteAuthorById(@PathVariable("authorID") long id) {
        authorService.delete(id);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/author")
    public void save(@RequestBody AuthorDTO authorDTO) throws Exception {
        authorService.create(authorDTO);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/author")
    public void updateBook(@RequestBody AuthorDTO authorDTO) {

        authorService.update(authorDTO);
    }

}