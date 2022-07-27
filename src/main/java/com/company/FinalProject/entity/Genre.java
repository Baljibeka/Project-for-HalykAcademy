package com.company.FinalProject.entity;

import com.company.FinalProject.dto.GenreDTO;

import javax.persistence.*;

@Entity
@Table(name="genre")
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GenreDTO convertToDto() {
        GenreDTO genreDTO = new GenreDTO();
        genreDTO.setName(this.getName());
        genreDTO.setId(this.getId());
        return genreDTO;
    }
}
