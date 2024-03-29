package com.company.FinalProject.entity;

import com.company.FinalProject.dto.Order.OrderAdminDTO;
import com.company.FinalProject.dto.Order.OrderDTO;
import com.company.FinalProject.dto.Order.OrderFullDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
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
    private LocalDateTime createdAt;
    @ManyToMany
    @JoinTable(
            name = "order_books",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id"))
    private List<Book> books;
    @Column(name="status")
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    public Order(Long id, User user, LocalDateTime createdAt, List<Book> books, OrderStatus status) {
        this.id = id;
        this.user = user;
        this.createdAt = createdAt;
        this.books = books;
        this.status = status;
    }

    public Order(Long id, User user, LocalDateTime createdAt, List<Book> books) {
        this.id = id;
        this.user = user;
        this.createdAt = createdAt;
        this.books = books;
    }

    public OrderDTO convertToDTO(){
        OrderDTO orderDTO=new OrderDTO();
        orderDTO.setId(this.getId());
        orderDTO.setBooks(this.getBooks().stream().map(Book::getId).toList());
        orderDTO.setUser(this.getUser().getId());
        orderDTO.setCreatedAt(this.getCreatedAt());
        return orderDTO;
    }
    public OrderAdminDTO convertToAdminDTO(){
        OrderAdminDTO orderAdminDTO=new OrderAdminDTO();
        orderAdminDTO.setId(this.getId());
        orderAdminDTO.setCreatedAt(this.getCreatedAt());
        orderAdminDTO.setBooks(this.getBooks().stream().map(Book::getId).toList());
        orderAdminDTO.setStatus(this.getStatus());
        orderAdminDTO.setUser(this.getUser().getId());
        return orderAdminDTO;
    }
    public OrderFullDTO convertToResponseDTO(){
        OrderFullDTO orderFullDTO =new OrderFullDTO();
        orderFullDTO.setId(this.getId());
        orderFullDTO.setBooks(this.getBooks().stream().map(Book::convertToShortDTO).toList());
        orderFullDTO.setStatus(this.getStatus());
        orderFullDTO.setUser(this.getUser().convertToDTO());
        orderFullDTO.setCreatedAt(this.getCreatedAt());
        return orderFullDTO;
    }
}
