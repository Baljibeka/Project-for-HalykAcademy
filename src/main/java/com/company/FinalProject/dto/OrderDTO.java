package com.company.FinalProject.dto;

import com.company.FinalProject.entity.Order;
import com.company.FinalProject.entity.OrderStatus;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderDTO {
    private long id;
    private UserDTO user;
    private List<BookResponseDTO> books;
    private OrderStatus status;
    private LocalDate createdAt;

    public Order convertToEntity(){
        Order order= new Order();
        order.setId(this.getId());
        order.setUser(this.getUser().convertToEntity());
        order.setStatus(this.getStatus());
        order.setBooks(this.getBooks().stream().map(BookResponseDTO::convertToEntity).toList());
        order.setCreatedAt(this.getCreatedAt());
        return order;
    }
}
