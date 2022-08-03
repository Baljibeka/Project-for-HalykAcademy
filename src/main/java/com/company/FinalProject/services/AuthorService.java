package com.company.FinalProject.services;

import com.company.FinalProject.dto.Author.AuthorDTO;
import com.company.FinalProject.dto.Author.AuthorResponseDTO;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Optional;

public interface AuthorService {
    public AuthorResponseDTO create(AuthorDTO authorDTO);
    public void delete(long id);
    public void update(AuthorDTO authorDTO);
    public Optional<AuthorDTO> findById(long id);
    List<AuthorDTO> findByFIO(String name);
    List<AuthorDTO> getAll();
    List<AuthorResponseDTO> getByGenreName(String genreName);

}
