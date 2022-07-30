package com.company.FinalProject.dto;

import com.company.FinalProject.entity.Genre;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
