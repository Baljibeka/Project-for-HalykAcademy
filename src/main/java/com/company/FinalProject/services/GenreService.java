package com.company.FinalProject.services;

import com.company.FinalProject.entity.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreService {
    public Genre create(Genre genre);
    public void delete(long id);
    public void update(Genre genre);
    public List<Genre> getAll();
}
