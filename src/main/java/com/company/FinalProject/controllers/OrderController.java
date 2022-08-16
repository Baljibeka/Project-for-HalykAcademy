package com.company.FinalProject.controllers;

import com.company.FinalProject.dto.Order.OrderAdminDTO;
import com.company.FinalProject.dto.Order.OrderDTO;
import com.company.FinalProject.dto.Order.OrderFullDTO;
import com.company.FinalProject.services.OrderService;
import com.company.FinalProject.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;
    private UserService userService;
    @Autowired
    public OrderController(OrderService orderService, UserService userService) {
        this.orderService = orderService;
        this.userService = userService;
    }
    @PostMapping("/order")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public void create(@RequestBody OrderDTO orderDTO) throws Exception {
        orderService.create(orderDTO);
    }
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    @PutMapping("/order")
    public void update(@RequestBody OrderDTO orderDTO) throws Exception {
            orderService.update(orderDTO);
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/order/{orderID}")
    public void delete(@PathVariable("orderID") long id){
        orderService.delete(id);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    @GetMapping
    public List<OrderFullDTO> getAll(){
        return orderService.getAll();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/admin/order")
    public void updateAdmin(@RequestBody OrderAdminDTO orderAdminDTO) throws Exception {
        orderService.updateAdmin(orderAdminDTO);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/order/{orderID}")
    public OrderFullDTO getByID(@PathVariable("orderID") long id){
        return orderService.getByID(id);
    }
}
