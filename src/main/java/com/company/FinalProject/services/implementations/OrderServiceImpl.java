package com.company.FinalProject.services.implementations;

import com.company.FinalProject.dto.OrderDTO;
import com.company.FinalProject.entity.Order;
import com.company.FinalProject.entity.Publisher;
import com.company.FinalProject.repo.OrderRepository;
import com.company.FinalProject.services.OrderService;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepo;

    public OrderServiceImpl(OrderRepository orderRepo) {
        this.orderRepo = orderRepo;
    }

    @Override
    public OrderDTO create(OrderDTO orderDTO) {
        Order order=orderDTO.convertToEntity();
        return orderRepo.save(order).convertToDTO();
    }

    @Override
    public void update(OrderDTO orderDTO, long id) {
        Order order=orderDTO.convertToEntity();
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
    public List<OrderDTO> getAll() {
        return orderRepo.findAll().stream().map(Order::convertToDTO).toList();
    }

    @Override
    public Optional<OrderDTO> getByID(long id) {
        return orderRepo.findById(id).map(Order::convertToDTO);
    }
}
