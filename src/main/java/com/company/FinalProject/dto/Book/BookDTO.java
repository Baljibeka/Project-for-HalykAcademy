package com.company.FinalProject.dto.Book;

import com.company.FinalProject.dto.Author.AuthorIdDTO;
import com.company.FinalProject.dto.Genre.GenreDTO;
import com.company.FinalProject.dto.Genre.GenreIdDTO;
import com.company.FinalProject.dto.Publisher.PublisherResponseDTO;
import com.company.FinalProject.entity.Book;
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
    private List<AuthorIdDTO> authorList;
    private PublisherResponseDTO publisher;
    private String name;
    private int numberOfPages;
    private LocalDate yearOfIssue;
    private List<GenreIdDTO> genreList;


    public Book convertToEntity() {
        Book book = new Book();
        book.setName(this.getName());
        if(this.getPublisher()!=null)
            book.setPublisher(this.getPublisher().convertToEntity());
        book.setPrice(this.getPrice());
        book.setYearOfIssue(this.getYearOfIssue());
        book.setId(this.getId());
        if(this.getGenreList()!=null)
            book.setBooksGenresList(this.getGenreList().stream().map(GenreIdDTO::convertToEntity).toList());
        if(this.getAuthorList()!=null)
            book.setAuthorList(this.getAuthorList().stream().map(AuthorIdDTO::convertToEntity).toList());
        book.setNumberOfPages(this.getNumberOfPages());
        return book;
    }
}
