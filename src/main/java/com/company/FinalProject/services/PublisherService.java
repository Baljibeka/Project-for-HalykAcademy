package com.company.FinalProject.services;


import com.company.FinalProject.dto.PublisherDTO;
import com.company.FinalProject.entity.Publisher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
public interface PublisherService {
    public List<PublisherDTO> getAll();
    public PublisherDTO create(PublisherDTO publisherDTO);
    public void delete(long id);
    public void update(PublisherDTO publisherDTO);
    public Optional<PublisherDTO> findById(long id);
    List<PublisherDTO> getByNameContaining(String name);
}
