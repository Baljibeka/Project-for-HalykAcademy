package com.company.FinalProject.services.implementations;

import com.company.FinalProject.dto.Genre.GenreDTO;
import com.company.FinalProject.entity.Book;
import com.company.FinalProject.entity.Genre;
import com.company.FinalProject.exception.DeletionException;
import com.company.FinalProject.exception.NotFoundException;
import com.company.FinalProject.repo.BookRepository;
import com.company.FinalProject.repo.GenreRepository;
import com.company.FinalProject.services.GenreService;
import lombok.RequiredArgsConstructor;
import org.postgresql.util.PSQLException;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepo;
    private final BookRepository bookRepo;
    @Override
    public GenreDTO create(GenreDTO genreDTO) {
        return genreRepo.save(genreDTO.convertToEntity()).convertToDto();
    }

    @Override
    public void delete(Long id) {
        if(!bookRepo.findAllByGenreId(id).isEmpty()) throw new DeletionException("Can't delete");
        else genreRepo.deleteById(id);
    }

    @Override
    public void update(GenreDTO genreDTO) {
        genreRepo.save(genreDTO.convertToEntity());
    }

    @Override
    public List<GenreDTO> getAll() {
        return genreRepo.findAll().stream().map(Genre::convertToDto).collect(Collectors.toList());
    }
}
