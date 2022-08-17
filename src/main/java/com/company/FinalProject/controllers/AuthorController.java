package com.company.FinalProject.controllers;

import com.company.FinalProject.dto.Author.AuthorDTO;
import com.company.FinalProject.dto.Author.AuthorFullDTO;
import com.company.FinalProject.dto.Author.AuthorShortDTO;
import com.company.FinalProject.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

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
    public List<AuthorFullDTO> getAll() {
        return authorService.getAll();
    }

    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    @GetMapping("/author/{authorID}")
    public AuthorFullDTO getAuthorById(@PathVariable("authorID") long id) {
        return authorService.findById(id);
    }

    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    @GetMapping("/author/name/{authorName}")
    public List<AuthorShortDTO> getAuthorByName(@PathVariable("authorName") String name) {
        return authorService.findByFIO(name);
    }

    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    @GetMapping("/author/genre/{genreName}")
    public Set<AuthorShortDTO> getAuthorByGenreName(@PathVariable("genreName") List<String> genreName)
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
    public void save(@RequestBody AuthorShortDTO authorShortDTO){
        authorService.create(authorShortDTO);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/author")
    public void updateBook(@RequestBody AuthorDTO authorDTO) {

        authorService.update(authorDTO);
    }

}