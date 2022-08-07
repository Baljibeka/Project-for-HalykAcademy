package com.company.FinalProject.dto.Author;

import com.company.FinalProject.entity.Author;
import lombok.Data;

@Data
public class AuthorShortDTO {
    private long id;
    private String surname;
    private String name;
    private String patronymic;

    public Author convertToEntity(){
        Author author=new Author();
        author.setName(this.getName());
        author.setId(this.getId());
        author.setPatronymic(this.getPatronymic());
        author.setSurname(this.getSurname());
        return author;
    }
}
