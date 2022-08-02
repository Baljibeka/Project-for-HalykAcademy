package com.company.FinalProject.dto.Publisher;

import com.company.FinalProject.dto.Book.BookResponseDTO;
import com.company.FinalProject.entity.Book;
import com.company.FinalProject.entity.Publisher;
import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PublisherDTO {
    private long id;

    private String name;

    private List<Long> publishedBooks;

    public Publisher convertToEntity(List<Book> books) {
        Publisher publisher = new Publisher();
        publisher.setName(this.getName());
        publisher.setId(this.getId());
        publisher.setPublishedBooksList(books);
        return publisher;
    }
}
