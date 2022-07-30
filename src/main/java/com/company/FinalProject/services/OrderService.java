package com.company.FinalProject.services;

import com.company.FinalProject.dto.OrderDTO;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    OrderDTO create(OrderDTO orderDTO);
    void update(OrderDTO orderDTO, long id);
    void delete(long id);
    List<OrderDTO> getAll();
    Optional<OrderDTO> getByID(long id);
}
