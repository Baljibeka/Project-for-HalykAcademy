package com.company.FinalProject.controllers;

import com.company.FinalProject.dto.AuthorDTO;
import com.company.FinalProject.dto.PublisherDTO;
import com.company.FinalProject.entity.Author;
import com.company.FinalProject.entity.Publisher;
import com.company.FinalProject.services.PublisherService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/publishers")
public class PublisherController {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private PublisherService publisherService;
    @Autowired
    public PublisherController(PublisherService publisherService){
        this.publisherService= publisherService;
    }

    @GetMapping("/publisher")
    public List<PublisherDTO> getAll(){
        List<Publisher> publishers=publisherService.getAll();
        return publishers.stream()
                .map(it->it.convertToDto(true))
                .collect(Collectors.toList());
    }
    @GetMapping("/publisher/{publisherID}")
    private Optional<PublisherDTO> getPublisherById(@PathVariable("publisherID") long id)
    {
        Optional<Publisher> publishers=publisherService.findById(id);
        return publishers.map(it->it.convertToDto(true));
    }

    @GetMapping("/publisher/{publisherName}")
    private List<PublisherDTO> getPublisherByName(@PathVariable("publisherName") String name) {
        List<Publisher> publishers = publisherService.getByNameContaining(name);
        return publishers
                .stream()
                .map(it->it.convertToDto(true))
                .collect(Collectors.toList());
    }

    @DeleteMapping("/publisher/{publisherID}")
    private void deletePublisherById(@PathVariable("publisherID") long id)
    {
        publisherService.delete(id);
    }
    @PostMapping("/publisher")
    private PublisherDTO savePublisher(@RequestBody PublisherDTO publisherDTO)
    {
        Publisher publisher = publisherDTO.convertToEntity();
        Publisher publisherCreated = publisherService.create(publisher);
        return publisherCreated.convertToDto(true);
    }
    @PutMapping("/publisher/{publisherID}")
    private void updatePublisher(@RequestBody PublisherDTO publisherDTO,@PathVariable("publisherID") long id)    {
        if(!Objects.equals(id, publisherDTO.getId())){
            throw new IllegalArgumentException("IDs don't match");
        }
        Publisher publisher = publisherDTO.convertToEntity();
        publisherService.update(publisher);
    }


}