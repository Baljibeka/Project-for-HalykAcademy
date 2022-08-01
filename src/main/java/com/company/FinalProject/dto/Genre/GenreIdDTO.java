package com.company.FinalProject.dto.Genre;

import com.company.FinalProject.entity.Genre;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class GenreIdDTO {
    private long id;
    public Genre convertToEntity(){
        Genre genre =new Genre();
        genre.setId(this.getId());
        return genre;
    }
}
