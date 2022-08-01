package com.company.FinalProject.dto.Book;

import com.company.FinalProject.entity.Book;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BookIdDTO {
    private long id;
    public Book convertToEntity(){
        Book book = new Book();
        book.setId(this.getId());
        return book;
    }
}
