package com.company.FinalProject.controllers;

import com.company.FinalProject.dto.PublisherDTO;
import com.company.FinalProject.services.PublisherService;

import java.util.Optional;

public class PublisherController {
    private PublisherService publisherService;
    public PublisherController(PublisherService publisherService){
        this.publisherService=publisherService;
    }
    public PublisherDTO createPublisher(PublisherDTO publisherDTO){
        return publisherService.createPublisher(publisherDTO);
    }
    public void deletePublisher(long id){
        publisherService.deletePublisher(id);
    }
    public PublisherDTO updatePublisher(PublisherDTO publisherDTO){
        return publisherService.updatePublisher(publisherDTO);
    }
    public Optional<PublisherDTO> findByIdPublisher(long id){
        return publisherService.findByIdPublisher(id);
    }
}
