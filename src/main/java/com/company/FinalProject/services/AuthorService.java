package com.company.FinalProject.services;

import com.company.FinalProject.dto.AuthorDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    public AuthorDTO create(AuthorDTO authorDTO);
    public void delete(long id);
    public void update(AuthorDTO authorDTO);
    public Optional<AuthorDTO> findById(long id);
    List<AuthorDTO> findByFIO(String name);
    List<AuthorDTO> getAll();
}
