package com.company.FinalProject.controllers;

import com.company.FinalProject.dto.GenreDTO;
import com.company.FinalProject.entity.Book;
import com.company.FinalProject.entity.Genre;
import com.company.FinalProject.services.GenreService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/genres")
public class GenreController {
    @Autowired
    private GenreService genreService;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    public GenreController(GenreService genreService){
        this.genreService=genreService;
    }

    @PostMapping("/genre")
    public GenreDTO create(@RequestBody GenreDTO genreDTO){
        Genre genre = genreDTO.convertToEntity();
        Genre genreCreated = genreService.create(genre);
        return genreCreated.convertToDto();
    }
    @DeleteMapping("/genre/{genreID}")
    public void delete(@PathVariable("genreID") long id){
        genreService.delete(id);
    }
    @PutMapping("/genre/{genreID}")
    public void update(@RequestBody GenreDTO genreDTO, @PathVariable("genreID") long id){
        if(!Objects.equals(id, genreDTO.getId())){
            throw new IllegalArgumentException("IDs don't match");
        }
        Genre genre = genreDTO.convertToEntity();
        genreService.update(genre);
    }
    @GetMapping("/genre")
    public List<GenreDTO> getAll(){
        List<Genre> genres = genreService.getAll();
        return genres.stream()
                .map(Genre::convertToDto)
                .collect(Collectors.toList());
    }

}
