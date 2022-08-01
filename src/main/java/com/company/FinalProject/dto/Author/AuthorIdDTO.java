package com.company.FinalProject.dto.Author;

import com.company.FinalProject.entity.Author;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AuthorIdDTO {
    private long id;
    public Author convertToEntity(){
        Author author = new Author();
        author.setId(this.getId());
        return author;
    }
}
