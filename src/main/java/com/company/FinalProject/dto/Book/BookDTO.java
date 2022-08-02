package com.company.FinalProject.dto.Book;

import com.company.FinalProject.dto.Publisher.PublisherResponseDTO;
import com.company.FinalProject.entity.Author;
import com.company.FinalProject.entity.Book;
import com.company.FinalProject.entity.Genre;
import com.company.FinalProject.entity.Publisher;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookDTO {
    private long id;
    private int price;
    private List<Long> authorList;
    private Long publisherId;
    private String name;
    private int numberOfPages;
    private LocalDate yearOfIssue;
    private List<Long> genreList;


    public Book convertToEntity(Publisher pub, List<Genre> genres, List<Author> authors) {
        Book book = new Book();
        book.setName(this.getName());
        book.setPrice(this.getPrice());
        book.setYearOfIssue(this.getYearOfIssue());
        book.setId(this.getId());
        book.setAuthorList(authors);
        book.setBooksGenresList(genres);
        book.setPublisher(pub);
        book.setNumberOfPages(this.getNumberOfPages());
        return book;
    }
}
