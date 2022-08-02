package com.company.FinalProject.controllers;

import com.company.FinalProject.dto.Publisher.PublisherDTO;
import com.company.FinalProject.dto.Publisher.PublisherResponseDTO;
import com.company.FinalProject.services.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/publishers")
public class PublisherController {
    @Autowired
    private PublisherService publisherService;
    @Autowired
    public PublisherController(PublisherService publisherService){
        this.publisherService= publisherService;
    }

    @GetMapping("/publisher")
    public List<PublisherResponseDTO> getAll(){
        return publisherService.getAll();
    }
    @GetMapping("/publisher/{publisherID}")
    public Optional<PublisherResponseDTO> getPublisherById(@PathVariable("publisherID") long id)
    {
        return publisherService.findById(id);
    }

    @GetMapping("/publisher/name/{publisherName}")
    public List<PublisherResponseDTO> getPublisherByName(@PathVariable("publisherName") String name) {
        return publisherService.getByNameContaining(name);
    }

    @DeleteMapping("/publisher/{publisherID}")
    public void deletePublisherById(@PathVariable("publisherID") long id)
    {
        publisherService.delete(id);
    }
    @PostMapping("/publisher")
    public PublisherResponseDTO savePublisher(@RequestBody PublisherDTO publisherDTO)
    {
        return publisherService.create(publisherDTO);
    }
    @PutMapping("/publisher")
    public void updatePublisher(@RequestBody PublisherDTO publisherDTO)    {
        publisherService.update(publisherDTO);
    }


}