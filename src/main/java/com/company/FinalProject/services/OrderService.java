package com.company.FinalProject.services;

import com.company.FinalProject.dto.Order.OrderAdminDTO;
import com.company.FinalProject.dto.Order.OrderDTO;
import com.company.FinalProject.dto.Order.OrderResponseDTO;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    void create(OrderDTO orderDTO) throws Exception;
    void update(OrderDTO orderDTO) throws Exception;
    void delete(long id);
    List<OrderResponseDTO> getAll();
    Optional<OrderResponseDTO> getByID(long id);
    void createAdmin(OrderAdminDTO orderAdminDTO) throws Exception;
    void updateAdmin(OrderAdminDTO orderAdminDTO) throws Exception;
}
