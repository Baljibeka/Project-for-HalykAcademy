package com.company.FinalProject.dto.Book;

import com.company.FinalProject.entity.Book;
import lombok.Data;

@Data
public class BookShortDTO {
    private long id;
    private String name;
    private int price;

    public Book convertToEntity(){
        Book book=new Book();
        book.setId(this.getId());
        book.setName(this.getName());
        book.setPrice(this.getPrice());
        return book;
    }
}
