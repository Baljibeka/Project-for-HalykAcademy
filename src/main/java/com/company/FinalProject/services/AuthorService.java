package com.company.FinalProject.services;

import com.company.FinalProject.dto.Author.AuthorDTO;
import com.company.FinalProject.dto.Author.AuthorResponseDTO;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    public void create(AuthorDTO authorDTO);
    public void delete(long id);
    public void update(AuthorDTO authorDTO);
    public Optional<AuthorResponseDTO> findById(long id);
    List<AuthorResponseDTO> findByFIO(String name);
    List<AuthorResponseDTO> getAll();
    List<AuthorResponseDTO> getByGenreName(String genreName);

}
