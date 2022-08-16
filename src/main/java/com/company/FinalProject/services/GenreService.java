package com.company.FinalProject.services;

import com.company.FinalProject.dto.Genre.GenreDTO;

import java.io.IOException;
import java.util.List;

public interface GenreService {
    public GenreDTO create(GenreDTO genreDTO);
    public void delete(Long id);
    public void update(GenreDTO genreDTO);
    public List<GenreDTO> getAll();
}
