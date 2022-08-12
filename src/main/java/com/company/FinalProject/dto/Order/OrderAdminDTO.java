package com.company.FinalProject.dto.Order;

import com.company.FinalProject.entity.Book;
import com.company.FinalProject.entity.Order;
import com.company.FinalProject.entity.OrderStatus;
import com.company.FinalProject.entity.User;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
@Data
public class OrderAdminDTO {
    private long id;
    private Long user;
    private List<Long> books;
    private LocalDateTime createdAt;
    private OrderStatus status;

    public Order convertToEntity(User user, List<Book> bookList){
        Order order= new Order();
        order.setId(this.getId());
        order.setUser(user);
        order.setBooks(bookList);
        order.setStatus(this.getStatus());
        order.setCreatedAt(this.getCreatedAt());
        return order;
    }
}
