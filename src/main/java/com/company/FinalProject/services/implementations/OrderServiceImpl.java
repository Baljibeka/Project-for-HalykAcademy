package com.company.FinalProject.services.implementations;

import com.company.FinalProject.dto.Order.OrderAdminDTO;
import com.company.FinalProject.dto.Order.OrderDTO;
import com.company.FinalProject.dto.Order.OrderFullDTO;
import com.company.FinalProject.entity.Book;
import com.company.FinalProject.entity.Order;
import com.company.FinalProject.entity.OrderStatus;
import com.company.FinalProject.entity.User;
import com.company.FinalProject.exception.*;
import com.company.FinalProject.repo.BookRepository;
import com.company.FinalProject.repo.OrderRepository;
import com.company.FinalProject.repo.UserRepository;
import com.company.FinalProject.services.OrderService;
import lombok.val;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

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

    public void logicChecking(OrderDTO orderDTO, Order order, User user, List<Book> books){
        int priceOfBooks = 0;
        if(orderDTO.getBooks().size()!=books.size()) throw new NotFoundException("Oops! Some of the books are not present");
        if (Objects.equals(SecurityContextHolder.getContext().getAuthentication().getName(), user.getLogin())) {
            for (Book book : order.getBooks()) {
                priceOfBooks += book.getPrice();
                if (!book.getIsEnabled()) throw new EnabledBookException("Book is deleted");
            }
            if (priceOfBooks > 10000)
            throw new UnacceptablePriceException("Price is " + priceOfBooks);
        }
        else throw new UserIsNotAllowedException("User is not allowed");
    }

    @Override
    public void create(OrderDTO orderDTO) throws Exception {
        val books = bookRepo.findAllById(orderDTO.getBooks());
        val user = userRepo.findById(orderDTO.getUser()).orElseThrow();
        Order order = orderDTO.convertToEntity(user, books);
        logicChecking(orderDTO, order, user, books);
                orderRepo.save(new Order(
                        order.getId(),
                        order.getUser(),
                        LocalDateTime.now(),
                        order.getBooks(),
                        OrderStatus.CREATED
                ));
    }

    @Override
    public void update(OrderDTO orderDTO) throws Exception {
        Order existingOrder = orderRepo.findById(orderDTO.getId()).orElseThrow();
        val books=bookRepo.findAllById(orderDTO.getBooks());
        val user=userRepo.findById(orderDTO.getUser()).orElseThrow();
        Order order=orderDTO.convertToEntity(user, books);
        logicChecking(orderDTO, order, user, books);
        if(!existingOrder.getStatus().equals(OrderStatus.CANCELLED)){
                    orderRepo.save(new Order(
                            order.getId(),
                            order.getUser(),
                            existingOrder.getCreatedAt(),
                            order.getBooks(),
                            existingOrder.getStatus()
                    ));
        }
        else throw new CancelledOrderException("Your order is cancelled");
    }

    @Override
    public void delete(long id) {
        Order order = orderRepo.findById(id).orElseThrow(()-> new NotFoundException("There is no such order"));
        orderRepo.save(new Order(order.getId(),
                order.getUser(),
                order.getCreatedAt(),
                order.getBooks(),
                OrderStatus.CANCELLED));
    }

    @Override
    public List<OrderFullDTO> getAll() {
        return orderRepo.findAll().stream().map(Order::convertToResponseDTO).toList();
    }

    @Override
    public OrderFullDTO getByID(long id) {
        Order order = orderRepo.findById(id).orElseThrow(()-> new NotFoundException("There is no such order"));
        return order.convertToResponseDTO();
    }

    @Override
    public void updateAdmin(OrderAdminDTO orderAdminDTO) throws Exception {
        val books=bookRepo.findAllById(orderAdminDTO.getBooks());
        val user=userRepo.findById(orderAdminDTO.getUser()).orElseThrow();
        Order existingOrder=orderRepo.findById(orderAdminDTO.getId()).orElseThrow();
        Order order=orderAdminDTO.convertToEntity(user, books);
        orderRepo.save(new Order(
                existingOrder.getId(),
                existingOrder.getUser(),
                existingOrder.getCreatedAt(),
                existingOrder.getBooks(),
                order.getStatus()
                ));

    }
}
