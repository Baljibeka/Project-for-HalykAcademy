package com.company.FinalProject.services.implementations;

import com.company.FinalProject.dto.Order.OrderDTO;
import com.company.FinalProject.dto.Order.OrderResponseDTO;
import com.company.FinalProject.entity.Book;
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
    public void create(OrderDTO orderDTO) throws Exception {
        int priceOfBooks=0;
        int existingBooks=0;
        val books=bookRepo.findAllById(orderDTO.getBooks());
        val users=userRepo.findById(orderDTO.getUser()).orElseThrow();
        Order order=orderDTO.convertToEntity(users, books);
        for(Book book:order.getBooks()){
            priceOfBooks+=book.getPrice();
            if(bookRepo.existsById(book.getId()))
                existingBooks+=1;
        }
        if(priceOfBooks<10000 && order.getUser().getIsBlocked()==false && existingBooks==order.getBooks().size())
            orderRepo.save(order);
        else
            throw new Exception("Price of the books is higher than 10K and is " + priceOfBooks + " or User is blocked");
    }

    @Override
    public void update(OrderDTO orderDTO) throws Exception {
        int priceOfBooks=0;
        int existingBooks=0;
        val books=bookRepo.findAllById(orderDTO.getBooks());
        val users=userRepo.findById(orderDTO.getUser()).orElseThrow();
        Order order=orderDTO.convertToEntity(users, books);
        for (Book book : order.getBooks()) {
            priceOfBooks += book.getPrice();
            if(bookRepo.existsById(book.getId()))
                existingBooks+=1;
        }
        if(order.getUser().getIsBlocked()==false) {
            if(existingBooks==order.getBooks().size() && priceOfBooks < 10000)
            orderRepo.save(order);
            else
                throw new Exception("Price of the books is higher than 10K and is " + priceOfBooks + " or book is deleted");
        }
        else throw new Exception("User is blocked");
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
