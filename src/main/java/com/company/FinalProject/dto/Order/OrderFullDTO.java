package com.company.FinalProject.dto.Order;

import com.company.FinalProject.dto.Book.BookShortDTO;
import com.company.FinalProject.dto.User.UserDTO;
import com.company.FinalProject.entity.Order;
import com.company.FinalProject.entity.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
@Data
public class OrderFullDTO {
    private long id;
    private UserDTO user;
    private List<BookShortDTO> books;
    private OrderStatus status;
    private LocalDateTime createdAt;

    public Order convertToEntity(){
        Order order = new Order();
        order.setId(this.getId());
        order.setUser(this.getUser().convertToEntity());
        order.setBooks(this.getBooks().stream().map(BookShortDTO::convertToEntity).toList());
        order.setStatus(this.getStatus());
        order.setCreatedAt(this.getCreatedAt());
        return order;
    }
}
