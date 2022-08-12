package com.company.FinalProject.services;

import com.company.FinalProject.dto.Author.AuthorDTO;
import com.company.FinalProject.dto.Author.AuthorResponseDTO;
import com.company.FinalProject.dto.Author.AuthorShortDTO;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface AuthorService {
    public void create(AuthorDTO authorDTO);
    public void delete(long id);
    public void update(AuthorDTO authorDTO);
    public AuthorResponseDTO findById(long id);
    List<AuthorResponseDTO> findByFIO(String name);
    List<AuthorResponseDTO> getAll();
    Set<AuthorShortDTO> getByGenreName(List<String> genreName);

}
