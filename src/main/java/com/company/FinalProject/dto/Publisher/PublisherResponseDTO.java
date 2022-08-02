package com.company.FinalProject.dto.Publisher;

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
public class PublisherResponseDTO {

    private long id;

    private String name;
    public Publisher convertToEntity(){
        Publisher publisher = new Publisher();
        publisher.setName(this.getName());
        publisher.setId(this.getId());
        return publisher;
    }
}
