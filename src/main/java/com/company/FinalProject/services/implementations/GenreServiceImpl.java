package com.company.FinalProject.services.implementations;

import com.company.FinalProject.entity.Genre;
import com.company.FinalProject.repo.GenreRepository;
import com.company.FinalProject.services.GenreService;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepo;
    public GenreServiceImpl(GenreRepository genreRepo){
        this.genreRepo=genreRepo;
    }
    @Override
    public Genre create(Genre genre) {
        return genreRepo.save(genre);
    }

    @Override
    public void delete(long id) {
        genreRepo.deleteById(id);
    }

    @Override
    public void update(Genre genre) {
        Genre existingGenre;
        try {
            existingGenre = (Genre) genreRepo.findById(genre.getId()).
                    orElseThrow(ChangeSetPersister.NotFoundException::new);
            existingGenre.setName(genre.getName());
            existingGenre.setId(genre.getId());
            genreRepo.save(genre);
        } catch (ChangeSetPersister.NotFoundException e) {
            e.printStackTrace();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Genre> getAll() {
        return genreRepo.findAll();
    }
}
