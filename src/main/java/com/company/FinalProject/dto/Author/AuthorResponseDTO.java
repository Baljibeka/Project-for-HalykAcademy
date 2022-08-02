package com.company.FinalProject.dto.Author;

import com.company.FinalProject.dto.Book.BookResponseDTO;
import com.company.FinalProject.dto.Genre.GenreDTO;
import com.company.FinalProject.entity.Author;

import java.time.LocalDate;
import java.util.List;

import lombok.*;


@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AuthorResponseDTO {
    private long id;
    private String surname;
    private String name;
    private String patronymic;
    private LocalDate dateOfBirth;
    private List<GenreDTO> genreList;

    public Author convertToEntity(){
        Author author = new Author();
        author.setName(this.getName());
        author.setId(this.getId());
        author.setPatronymic(this.getPatronymic());
        author.setSurname(this.getSurname());
        author.setDateOfBirth(this.getDateOfBirth());
        return author;
    }
}
