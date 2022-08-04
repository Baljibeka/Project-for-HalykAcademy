package com.company.FinalProject.services.implementations;

import com.company.FinalProject.dto.Genre.GenreDTO;
import com.company.FinalProject.entity.Genre;
import com.company.FinalProject.repo.GenreRepository;
import com.company.FinalProject.services.GenreService;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepo;
    public GenreServiceImpl(GenreRepository genreRepo){
        this.genreRepo=genreRepo;
    }
    @Override
    public GenreDTO create(GenreDTO genreDTO) {
        Genre genre = genreDTO.convertToEntity();
        Genre genreCreated = genreRepo.save(genre);
        return genreCreated.convertToDto();
    }

    @Override
    public void delete(long id) {
        genreRepo.deleteById(id);
    }

    @Override
    public void update(GenreDTO genreDTO) {
        Genre genre=genreDTO.convertToEntity();
        Genre existingGenre;
        genreRepo.save(genre);
    }

    @Override
    public List<GenreDTO> getAll() {
        return genreRepo.findAll().stream().map(Genre::convertToDto).collect(Collectors.toList());
    }
}
