package com.company.FinalProject.services;

import com.company.FinalProject.dto.AuthorDTO;

import java.util.Optional;

public interface AuthorService {
    public AuthorDTO createAuthor(AuthorDTO authorDTO);
    public void deleteAuthor(long id);
    public AuthorDTO updateAuthor(AuthorDTO authorDTO);
    public Optional<AuthorDTO> findByIdAuthor(long id);
}
