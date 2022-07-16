package com.company.FinalProject.services;


import com.company.FinalProject.dto.PublisherDTO;

import java.util.List;
import java.util.Optional;

public interface PublisherService {
    public PublisherDTO createPublisher(PublisherDTO publisherDTO);
    public void deletePublisher(long id);
    public PublisherDTO updatePublisher(PublisherDTO publisherDTO);
    public Optional<PublisherDTO> findByIdPublisher(long id);
    public List<PublisherDTO> findByName(String name);
}
