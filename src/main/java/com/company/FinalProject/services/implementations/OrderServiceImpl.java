package com.company.FinalProject.services.implementations;

import com.company.FinalProject.dto.Order.OrderAdminDTO;
import com.company.FinalProject.dto.Order.OrderDTO;
import com.company.FinalProject.dto.Order.OrderResponseDTO;
import com.company.FinalProject.entity.Book;
import com.company.FinalProject.entity.Order;
import com.company.FinalProject.entity.OrderStatus;
import com.company.FinalProject.repo.BookRepository;
import com.company.FinalProject.repo.OrderRepository;
import com.company.FinalProject.repo.UserRepository;
import com.company.FinalProject.services.OrderService;
import lombok.val;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepo;
    private final BookRepository bookRepo;
    private final UserRepository userRepo;

    public OrderServiceImpl(OrderRepository orderRepo, BookRepository bookRepo, UserRepository userRepo) {
        this.orderRepo = orderRepo;
        this.bookRepo=bookRepo;
        this.userRepo=userRepo;
    }

    @Override
    public void create(OrderDTO orderDTO) throws Exception {
        int priceOfBooks = 0;
        int existingBooks = 0;
        val books = bookRepo.findAllById(orderDTO.getBooks());
        val user = userRepo.findById(orderDTO.getUser()).orElseThrow();
        Order order = orderDTO.convertToEntity(user, books);
        if (Objects.equals(SecurityContextHolder.getContext().getAuthentication().getName(), user.getLogin())) {
            for (Book book : order.getBooks()) {
                priceOfBooks += book.getPrice();
                if (bookRepo.existsById(book.getId()))
                    existingBooks += 1;
            }
            if (priceOfBooks < 10000 && order.getUser().getIsBlocked() == false && existingBooks == order.getBooks().size())
                orderRepo.save(new Order(
                        order.getId(),
                        order.getUser(),
                        LocalDateTime.now(),
                        order.getBooks(),
                        OrderStatus.CREATED
                ));
            else
                throw new Exception("Price of the books is higher than 10K and is " + priceOfBooks + " or User is blocked");
        }
         else throw new Exception("User is not allowed");

    }

    @Override
    public void update(OrderDTO orderDTO) throws Exception {
        int priceOfBooks=0;
        int existingBooks=0;
        Order existingOrder = orderRepo.findById(orderDTO.getId()).orElseThrow();
        val books=bookRepo.findAllById(orderDTO.getBooks());
        val user=userRepo.findById(orderDTO.getUser()).orElseThrow();
        Order order=orderDTO.convertToEntity(user, books);
        if (Objects.equals(SecurityContextHolder.getContext().getAuthentication().getName(), existingOrder.getUser().getLogin())) {
            for (Book book : order.getBooks()) {
                priceOfBooks += book.getPrice();
                if (bookRepo.existsById(book.getId()))
                    existingBooks += 1;
            }
            if (order.getUser().getIsBlocked().equals(false)) {
                if (existingBooks == order.getBooks().size() && priceOfBooks < 10000)
                    orderRepo.save(new Order(
                            order.getId(),
                            order.getUser(),
                            existingOrder.getCreatedAt(),
                            order.getBooks(),
                            existingOrder.getStatus()
                    ));
                else
                    throw new Exception("Price of the books is higher than 10K and is " + priceOfBooks + " or book is deleted");

            }
            else throw new Exception("User is blocked");
        }
        else throw new Exception("User is not allowed");
    }

    @Override
    public void delete(long id) {
        orderRepo.deleteById(id);
    }

    @Override
    public List<OrderResponseDTO> getAll() {
        return orderRepo.findAll().stream().map(Order::convertToResponseDTO).toList();
    }

    @Override
    public Optional<OrderResponseDTO> getByID(long id) {
        return orderRepo.findById(id).map(Order::convertToResponseDTO);
    }

    @Override
    public void createAdmin(OrderAdminDTO orderAdminDTO) throws Exception {
        int priceOfBooks=0;
        int existingBooks=0;
        val books=bookRepo.findAllById(orderAdminDTO.getBooks());
        val user=userRepo.findById(orderAdminDTO.getUser()).orElseThrow();
        Order order=orderAdminDTO.convertToEntity(user, books);
        if (Objects.equals(SecurityContextHolder.getContext().getAuthentication().getName(), user.getLogin())) {
            for (Book book : order.getBooks()) {
                priceOfBooks += book.getPrice();
                if (bookRepo.existsById(book.getId()))
                    existingBooks += 1;
            }
            if (priceOfBooks < 10000 && order.getUser().getIsBlocked() == false && existingBooks == order.getBooks().size())
                orderRepo.save(new Order(
                        order.getId(),
                        order.getUser(),
                        LocalDateTime.now(),
                        order.getBooks(),
                        order.getStatus()
                ));
            else throw new Exception("Price of the books is higher than 10K and is " + priceOfBooks + " or User is blocked");
        }
        else throw new Exception("You can't create an order for someone else");
    }

    @Override
    public void updateAdmin(OrderAdminDTO orderAdminDTO) throws Exception {
        int priceOfBooks=0;
        int existingBooks=0;
        val books=bookRepo.findAllById(orderAdminDTO.getBooks());
        val user=userRepo.findById(orderAdminDTO.getUser()).orElseThrow();
        Order existingOrder=orderRepo.findById(orderAdminDTO.getId()).orElseThrow();
        Order order=orderAdminDTO.convertToEntity(user, books);
        for (Book book : order.getBooks()) {
            priceOfBooks += book.getPrice();
            if (bookRepo.existsById(book.getId()))
                existingBooks += 1;
        }
        if (Objects.equals(SecurityContextHolder.getContext().getAuthentication().getName(), existingOrder.getUser().getLogin())) {
            if (priceOfBooks < 10000 && order.getUser().getIsBlocked() == false && existingBooks == order.getBooks().size())
                orderRepo.save(new Order(
                        order.getId(),
                        order.getUser(),
                        existingOrder.getCreatedAt(),
                        order.getBooks(),
                        order.getStatus()
                ));
            else
                throw new Exception("Price of the books is higher than 10K and is " + priceOfBooks + " or User is blocked");
        }
        else{
            if (priceOfBooks < 10000 && order.getUser().getIsBlocked() == false && existingBooks == order.getBooks().size()){
                orderRepo.save(new Order(
                        existingOrder.getId(),
                        existingOrder.getUser(),
                        existingOrder.getCreatedAt(),
                        existingOrder.getBooks(),
                        order.getStatus()
                ));
            }
        }
    }
}
