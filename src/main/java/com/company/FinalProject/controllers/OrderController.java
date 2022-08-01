package com.company.FinalProject.controllers;

import com.company.FinalProject.dto.Order.OrderDTO;
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
    private OrderDTO create(@RequestBody OrderDTO orderDTO){
        return orderService.create(orderDTO);
    }
    @PutMapping("order/{orderID}")
    private void update(@RequestBody OrderDTO orderDTO,@PathVariable("orderID") long id){
        if(!Objects.equals(id, orderDTO.getId())){
            throw new IllegalArgumentException("IDs don't match");
        }
        orderService.update(orderDTO, id);
    }
    @DeleteMapping("/order/{orderID}")
    private void delete(@PathVariable("orderID") long id){
        orderService.delete(id);
    }
    @GetMapping("/order")
    private List<OrderDTO> getAll(){
        return orderService.getAll();
    }
    @GetMapping("/order/{orderID}")
    private Optional<OrderDTO> getByID(@PathVariable("orderID") long id){
        return orderService.getByID(id);
    }
}
