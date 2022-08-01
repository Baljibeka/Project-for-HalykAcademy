package com.company.FinalProject.dto;

import com.company.FinalProject.entity.Publisher;
import lombok.*;


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