package com.company.FinalProject.dto;

import com.company.FinalProject.entity.Genre;

public class GenreDTO {
    private long id;
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

    public Genre convertToEntity() {
        Genre genre = new Genre();
        genre.setName(this.getName());
        genre.setId(this.getId());
        return genre;
    }
}
