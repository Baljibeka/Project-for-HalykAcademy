package com.company.FinalProject.services;


import com.company.FinalProject.dto.Publisher.PublisherDTO;
import com.company.FinalProject.dto.Publisher.PublisherResponseDTO;

import java.util.List;
import java.util.Optional;
public interface PublisherService {
    public List<PublisherResponseDTO> getAll();
    public PublisherResponseDTO create(PublisherDTO publisherDTO);
    public void delete(long id);
    public void update(PublisherDTO publisherDTO);
    public Optional<PublisherResponseDTO> findById(long id);
    List<PublisherResponseDTO> getByNameContaining(String name);
}
