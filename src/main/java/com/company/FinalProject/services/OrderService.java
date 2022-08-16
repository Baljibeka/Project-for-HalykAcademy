package com.company.FinalProject.services;

import com.company.FinalProject.dto.Order.OrderAdminDTO;
import com.company.FinalProject.dto.Order.OrderDTO;
import com.company.FinalProject.dto.Order.OrderFullDTO;

import java.util.List;

public interface OrderService {
    void create(OrderDTO orderDTO) throws Exception;
    void update(OrderDTO orderDTO) throws Exception;
    void delete(long id);
    List<OrderFullDTO> getAll();
    OrderFullDTO getByID(long id);
    void updateAdmin(OrderAdminDTO orderAdminDTO) throws Exception;
}
