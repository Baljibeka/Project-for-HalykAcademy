package com.company.FinalProject.services;


import com.company.FinalProject.dto.PublisherDTO;
import com.company.FinalProject.entity.Publisher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
public interface PublisherService {
    public List<Publisher> getAll();
    public Publisher create(Publisher publisher);
    public void delete(long id);
    public void update(Publisher publisher);
    public Optional<Publisher> findById(long id);
    List<Publisher> getByNameContaining(String name);
}
