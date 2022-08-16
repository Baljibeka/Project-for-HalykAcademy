package com.company.FinalProject.controllers;

import com.company.FinalProject.dto.Genre.GenreDTO;
import com.company.FinalProject.services.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/genre")
    public GenreDTO create(@RequestBody GenreDTO genreDTO){
        return genreService.create(genreDTO);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/genre/{genreID}")
    public void delete(@PathVariable("genreID") Long id){
        genreService.delete(id);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/genre")
    public void update(@RequestBody GenreDTO genreDTO){
        genreService.update(genreDTO);
    }

    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    @GetMapping
    public List<GenreDTO> getAll(){
        return genreService.getAll();
    }

}
