package com.company.FinalProject.controllers;

import com.company.FinalProject.dto.Order.OrderDTO;
import com.company.FinalProject.dto.Order.OrderResponseDTO;
import com.company.FinalProject.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public OrderResponseDTO create(@RequestBody OrderDTO orderDTO){
        return orderService.create(orderDTO);
    }
    @PutMapping("order")
    public void update(@RequestBody OrderDTO orderDTO){
        orderService.update(orderDTO);
    }
    @DeleteMapping("/order/{orderID}")
    public void delete(@PathVariable("orderID") long id){
        orderService.delete(id);
    }
    @GetMapping("/order")
    public List<OrderResponseDTO> getAll(){
        return orderService.getAll();
    }
    @GetMapping("/order/{orderID}")
    public Optional<OrderResponseDTO> getByID(@PathVariable("orderID") long id){
        return orderService.getByID(id);
    }
}
