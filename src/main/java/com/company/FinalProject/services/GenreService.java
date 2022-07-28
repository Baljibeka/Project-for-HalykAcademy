package com.company.FinalProject.services;

import com.company.FinalProject.dto.GenreDTO;

import java.util.List;
import java.util.Optional;

public interface GenreService {
    public GenreDTO create(GenreDTO genreDTO);
    public void delete(long id);
    public void update(GenreDTO genreDTO);
    public List<GenreDTO> getAll();
}
