package com.company.FinalProject.services;

import com.company.FinalProject.dto.Author.AuthorDTO;
import com.company.FinalProject.dto.Author.AuthorFullDTO;
import com.company.FinalProject.dto.Author.AuthorShortDTO;

import java.util.List;
import java.util.Set;

public interface AuthorService {
    public void create(AuthorShortDTO authorShortDTO);
    public void delete(long id);
    public void update(AuthorDTO authorDTO);
    public AuthorFullDTO findById(long id);
    List<AuthorShortDTO> findByFIO(String name);
    List<AuthorFullDTO> getAll();
    Set<AuthorShortDTO> getByGenreName(List<String> genreName);

}
