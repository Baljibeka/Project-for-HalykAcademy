package com.company.FinalProject.services.implementations;

import com.company.FinalProject.dto.Order.OrderDTO;
import com.company.FinalProject.dto.Order.OrderResponseDTO;
import com.company.FinalProject.entity.Order;
import com.company.FinalProject.repo.BookRepository;
import com.company.FinalProject.repo.OrderRepository;
import com.company.FinalProject.repo.UserRepository;
import com.company.FinalProject.services.OrderService;
import lombok.val;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
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
    public OrderResponseDTO create(OrderDTO orderDTO) {
        val books=bookRepo.findAllById(orderDTO.getBooks());
        val users=userRepo.findById(orderDTO.getUser()).orElseThrow();
        Order order=orderDTO.convertToEntity(users, books);
        return orderRepo.save(order).convertToResponseDTO();
    }

    @Override
    public void update(OrderDTO orderDTO) {
        val books=bookRepo.findAllById(orderDTO.getBooks());
        val users=userRepo.findById(orderDTO.getUser()).orElseThrow();
        Order order=orderDTO.convertToEntity(users, books);
        Order existingOrder = null;
        try {
            existingOrder = orderRepo.findById(order.getId()).orElseThrow(ChangeSetPersister.NotFoundException::new);
            existingOrder.setCreatedAt(order.getCreatedAt());
            existingOrder.setBooks(order.getBooks());
            existingOrder.setStatus(order.getStatus());
            existingOrder.setUser(order.getUser());
            orderRepo.save(order);
        } catch (ChangeSetPersister.NotFoundException e) {
            e.printStackTrace();
        }
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
}
