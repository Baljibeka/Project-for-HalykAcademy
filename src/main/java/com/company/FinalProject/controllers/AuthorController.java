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
    public List<AuthorDTO> getAll() {
        return authorService.getAll();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/author/{authorID}")
    private Optional<AuthorDTO> getAuthorById(@PathVariable("authorID") long id) {
        return authorService.findById(id);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/author/name/{authorName}")
    private List<AuthorDTO> getAuthorByName(@PathVariable("authorName") String name) {
        return authorService.findByFIO(name);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/author/genre/{genreName}")
    private List<AuthorResponseDTO> getAuthorByGenreName(@PathVariable("genreName") String genreName)
    {
        return authorService.getByGenreName(genreName);

    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/author/{authorID}")
    private void deleteAuthorById(@PathVariable("authorID") long id) {
        authorService.delete(id);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/author")
    private AuthorResponseDTO save(@RequestBody AuthorDTO authorDTO) throws Exception {
        return authorService.create(authorDTO);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/author")
    private void updateBook(@RequestBody AuthorDTO authorDTO) {

        authorService.update(authorDTO);
    }

}