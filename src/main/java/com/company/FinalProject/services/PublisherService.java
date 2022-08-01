package com.company.FinalProject.services;


import com.company.FinalProject.dto.Publisher.PublisherDTO;
import com.company.FinalProject.dto.Publisher.PublisherResponseDTO;

import java.util.List;
import java.util.Optional;
public interface PublisherService {
    public List<PublisherDTO> getAll();
    public PublisherResponseDTO create(PublisherResponseDTO publisherResponseDTO);
    public void delete(long id);
    public void update(PublisherDTO publisherDTO, long id);
    public Optional<PublisherDTO> findById(long id);
    List<PublisherResponseDTO> getByNameContaining(String name);
}
