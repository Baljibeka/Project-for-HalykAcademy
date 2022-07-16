package com.company.FinalProject.controllers;

import com.company.FinalProject.dto.AuthorDTO;
import com.company.FinalProject.services.AuthorService;

import java.util.Optional;

public class AuthorController {
    private AuthorService authorService;
    public AuthorController(AuthorService authorService){
        this.authorService=authorService;
    }
    public AuthorDTO createAuthor(AuthorDTO authorDTO){
        return authorService.createAuthor(authorDTO);
    }
    public void deleteAuthor(long id){
        authorService.deleteAuthor(id);
    }
    public AuthorDTO updateAuthor(AuthorDTO authorDTO){
        return authorService.updateAuthor(authorDTO);
    }
    public Optional<AuthorDTO> findByIdAuthor(long id){
        return authorService.findByIdAuthor(id);
    }
}
