package com.company.FinalProject.dto.Order;

import com.company.FinalProject.dto.User.UserDTO;
import com.company.FinalProject.entity.Book;
import com.company.FinalProject.entity.Order;
import com.company.FinalProject.entity.OrderStatus;
import com.company.FinalProject.entity.User;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
public class OrderDTO {
    private long id;
    private Long user;
    private List<Long> books;
    private LocalDateTime createdAt;

    public Order convertToEntity(User user, List<Book> bookList){
        Order order= new Order();
        order.setId(this.getId());
        order.setUser(user);
        order.setBooks(bookList);
        order.setCreatedAt(this.getCreatedAt());
        return order;
    }
}
