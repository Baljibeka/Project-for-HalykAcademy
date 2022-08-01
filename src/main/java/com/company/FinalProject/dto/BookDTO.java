package com.company.FinalProject.dto;

import com.company.FinalProject.entity.Author;
import com.company.FinalProject.entity.Book;
import com.company.FinalProject.entity.Genre;
import com.company.FinalProject.entity.Publisher;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookDTO {
    private long id;
    private int price;
    private List<AuthorResponseDTO> authorList;
    private PublisherResponseDTO publisher;
    private String name;
    private int numberOfPages;
    private LocalDate yearOfIssue;
    private List<GenreDTO> genreList;


    public Book convertToEntity() {
        Book book = new Book();
        book.setName(this.getName());
        if(this.getPublisher()!=null)
            book.setPublisher(this.getPublisher().convertToEntity());
        book.setPrice(this.getPrice());
        book.setYearOfIssue(this.getYearOfIssue());
        book.setId(this.getId());
        if(this.getGenreList()!=null)
            book.setBooksGenresList(this.getGenreList().stream().map(GenreDTO::convertToEntity).toList());
        if(this.getAuthorList()!=null)
            book.setAuthorList(this.getAuthorList().stream().map(AuthorResponseDTO::convertToEntity).toList());
        book.setNumberOfPages(this.getNumberOfPages());
        return book;
    }
}
