package com.company.FinalProject.controllers;

import com.company.FinalProject.dto.Order.OrderDTO;
import com.company.FinalProject.dto.Order.OrderResponseDTO;
import com.company.FinalProject.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }
    @PostMapping("/order")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void create(@RequestBody OrderDTO orderDTO) throws Exception {
        orderService.create(orderDTO);
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("order")
    public void update(@RequestBody OrderDTO orderDTO) throws Exception {
        orderService.update(orderDTO);
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/order/{orderID}")
    public void delete(@PathVariable("orderID") long id){
        orderService.delete(id);
    }
    @GetMapping("/order")
    public List<OrderResponseDTO> getAll(){
        return orderService.getAll();
    }
    @GetMapping("/order/{orderID}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Optional<OrderResponseDTO> getByID(@PathVariable("orderID") long id){
        return orderService.getByID(id);
    }
}
