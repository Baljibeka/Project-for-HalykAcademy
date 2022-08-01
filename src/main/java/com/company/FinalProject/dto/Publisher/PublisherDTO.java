package com.company.FinalProject.dto.Publisher;

import com.company.FinalProject.dto.Book.BookIdDTO;
import com.company.FinalProject.dto.Book.BookResponseDTO;
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

    private List<BookIdDTO> publishedBooks;

    public Publisher convertToEntity() {
        Publisher publisher = new Publisher();
        publisher.setName(this.getName());
        publisher.setId(this.getId());
        if(this.getPublishedBooks()!=null)
            publisher.setPublishedBooksList(this.getPublishedBooks().stream().map(BookIdDTO::convertToEntity).toList());
        return publisher;
    }
}
