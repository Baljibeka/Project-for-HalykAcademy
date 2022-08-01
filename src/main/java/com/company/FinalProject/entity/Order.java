package com.company.FinalProject.entity;

import com.company.FinalProject.dto.Order.OrderDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="orders")
public class Order {
    @Id
    @SequenceGenerator(
        name="order_sequence",
        sequenceName="order_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "order_sequence"
    )
    @Column(name="order_id")
    private long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @Column(name="created_at")
    private LocalDate createdAt;
    @ManyToMany
    @JoinTable(
            name = "order_books",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id"))
    private List<Book> books;
    @Column(name="status")
    private OrderStatus status;

    public OrderDTO convertToDTO(){
        OrderDTO orderDTO=new OrderDTO();
        orderDTO.setId(this.getId());
        orderDTO.setBooks(this.getBooks().stream().map(Book::convertToIdDTO).toList());
        orderDTO.setUser(this.getUser().convertToDTO());
        orderDTO.setStatus(this.getStatus());
        orderDTO.setCreatedAt(this.getCreatedAt());
        return orderDTO;
    }
}
