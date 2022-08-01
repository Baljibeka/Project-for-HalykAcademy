package com.company.FinalProject.dto.Order;

import com.company.FinalProject.dto.Book.BookIdDTO;
import com.company.FinalProject.dto.Book.BookResponseDTO;
import com.company.FinalProject.dto.User.UserDTO;
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
    private List<BookIdDTO> books;
    private OrderStatus status;
    private LocalDate createdAt;

    public Order convertToEntity(){
        Order order= new Order();
        order.setId(this.getId());
        order.setUser(this.getUser().convertToEntity());
        order.setStatus(this.getStatus());
        order.setBooks(this.getBooks().stream().map(BookIdDTO::convertToEntity).toList());
        order.setCreatedAt(this.getCreatedAt());
        return order;
    }
}
