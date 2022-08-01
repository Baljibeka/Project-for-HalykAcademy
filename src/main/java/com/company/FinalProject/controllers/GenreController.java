package com.company.FinalProject.controllers;

import com.company.FinalProject.dto.Genre.GenreDTO;
import com.company.FinalProject.services.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/genres")
public class GenreController {
    @Autowired
    private GenreService genreService;
    @Autowired
    public GenreController(GenreService genreService){
        this.genreService=genreService;
    }

    @PostMapping("/genre")
    public GenreDTO create(@RequestBody GenreDTO genreDTO){
        return genreService.create(genreDTO);
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
        genreService.update(genreDTO);
    }
    @GetMapping("/genre")
    public List<GenreDTO> getAll(){
        return genreService.getAll();
    }

}
