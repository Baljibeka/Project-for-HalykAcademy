package com.company.FinalProject.dto.Order;

import com.company.FinalProject.dto.Book.BookResponseDTO;
import com.company.FinalProject.dto.User.UserDTO;
import com.company.FinalProject.entity.Order;
import com.company.FinalProject.entity.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponseDTO {
    private long id;
    private UserDTO user;
    private List<BookResponseDTO> books;
    private OrderStatus status;
    private LocalDate createdAt;

    public Order convertToEntity(){
        Order order = new Order();
        order.setId(this.getId());
        order.setUser(this.getUser().convertToEntity());
        order.setBooks(this.getBooks().stream().map(BookResponseDTO::convertToEntity).toList());
        order.setStatus(this.getStatus());
        order.setCreatedAt(this.getCreatedAt());
        return order;
    }
}
