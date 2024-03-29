package com.company.FinalProject.entity;

import com.company.FinalProject.dto.Genre.GenreDTO;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="genre")
@Data
public class Genre {
    @Id
    @SequenceGenerator(
            name="genre_sequence",
            sequenceName="genre_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "genre_sequence"
    )
    @Column(name="genre_id")
    private long id;
    @Column(name="name")
    private String name;

    public GenreDTO convertToDto() {
        GenreDTO genreDTO = new GenreDTO();
        genreDTO.setName(this.getName());
        genreDTO.setId(this.getId());
        return genreDTO;
    }
}
