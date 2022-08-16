package com.company.FinalProject.dto.Publisher;

import com.company.FinalProject.entity.Publisher;
import lombok.*;

import java.util.List;


@Data
public class PublisherShortDTO {

    private long id;
    private String name;

    public Publisher convertToEntity(){
        Publisher publisher = new Publisher();
        publisher.setName(this.getName());
        publisher.setId(this.getId());
        return publisher;
    }
}
