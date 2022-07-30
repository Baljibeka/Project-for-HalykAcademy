package com.company.FinalProject.services;

import com.company.FinalProject.dto.AuthorDTO;
import com.company.FinalProject.dto.AuthorResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    public AuthorResponseDTO create(AuthorResponseDTO authorResponseDTO);
    public void delete(long id);
    public void update(AuthorDTO authorDTO, long id);
    public Optional<AuthorDTO> findById(long id);
    List<AuthorDTO> findByFIO(String name);
    List<AuthorDTO> getAll();
    List<AuthorResponseDTO> getByGenreName(String genreName);

}
