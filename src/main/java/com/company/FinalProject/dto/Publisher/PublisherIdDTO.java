package com.company.FinalProject.dto.Publisher;

import com.company.FinalProject.entity.Publisher;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PublisherIdDTO {
    private long id;
    public Publisher convertToEntity(){
        Publisher publisher=new Publisher();
        publisher.setId(this.getId());
        return publisher;
    }
}
