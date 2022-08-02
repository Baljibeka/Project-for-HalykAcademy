package com.company.FinalProject.services;

import com.company.FinalProject.dto.Order.OrderDTO;
import com.company.FinalProject.dto.Order.OrderResponseDTO;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    OrderResponseDTO create(OrderDTO orderDTO);
    void update(OrderDTO orderDTO);
    void delete(long id);
    List<OrderResponseDTO> getAll();
    Optional<OrderResponseDTO> getByID(long id);
}
