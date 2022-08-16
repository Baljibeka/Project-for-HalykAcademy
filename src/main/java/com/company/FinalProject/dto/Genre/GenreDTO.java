package com.company.FinalProject.dto.Genre;

import com.company.FinalProject.entity.Genre;
import lombok.*;

@Data
public class GenreDTO {
    private long id;
    private String name;

    public Genre convertToEntity() {
        Genre genre = new Genre();
        genre.setName(this.getName());
        genre.setId(this.getId());
        return genre;
    }
}
