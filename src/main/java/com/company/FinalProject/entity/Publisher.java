package com.company.FinalProject.entity;

import com.company.FinalProject.dto.Publisher.PublisherDTO;
import com.company.FinalProject.dto.Publisher.PublisherShortDTO;
import lombok.*;

import javax.persistence.*;
import java.util.List;
@Entity
@Data
@Table(name = "PUBLISHER")
public class Publisher {
    @Id
    @SequenceGenerator(
            name="publisher_sequence",
            sequenceName="publisher_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "publisher_sequence"
    )
    @Column(name="publisher_id")
    private long id;
    @Column(name="name")
    private String name;
    @OneToMany(mappedBy = "publisher")
    private List<Book> publishedBooksList;
    @Column(name="is_blocked")
    private Boolean isBlocked;

    public PublisherDTO convertToDto() {
        PublisherDTO publisherDTO = new PublisherDTO();
        publisherDTO.setName(this.getName());
        publisherDTO.setId(this.getId());
        publisherDTO.setPublishedBooks(this.getPublishedBooksList().stream().map(Book::getId).toList());
        return publisherDTO;
    }

    public PublisherShortDTO convertToResponseDto() {
        PublisherShortDTO publisherShortDTO = new PublisherShortDTO();
        publisherShortDTO.setName(this.getName());
        publisherShortDTO.setId(this.getId());
        return publisherShortDTO;
    }
}
