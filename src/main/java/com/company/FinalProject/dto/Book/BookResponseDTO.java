package com.company.FinalProject.dto.Book;

import com.company.FinalProject.dto.Genre.GenreDTO;
import com.company.FinalProject.dto.Publisher.PublisherResponseDTO;
import com.company.FinalProject.entity.Book;

import java.time.LocalDate;
import java.util.List;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BookResponseDTO {

    private long id;
    private int price;
    private PublisherResponseDTO publisher;
    private String name;
    private int numberOfPages;
    private LocalDate yearOfIssue;
    private List<GenreDTO> genreList;

    public Book convertToEntity(){
        Book book=new Book();
        book.setName(this.getName());
        book.setPublisher(this.getPublisher().convertToEntity());
        book.setPrice(this.getPrice());
        book.setYearOfIssue(this.getYearOfIssue());
        book.setId(this.getId());
        book.setNumberOfPages(this.getNumberOfPages());
        book.setBooksGenresList(this.getGenreList().stream().map(GenreDTO::convertToEntity).toList());
        return book;
    }
}
