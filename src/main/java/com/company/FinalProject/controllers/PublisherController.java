package com.company.FinalProject.controllers;

import com.company.FinalProject.dto.Publisher.PublisherDTO;
import com.company.FinalProject.dto.Publisher.PublisherShortDTO;
import com.company.FinalProject.services.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/publishers")
public class PublisherController {
    @Autowired
    private PublisherService publisherService;
    @Autowired
    public PublisherController(PublisherService publisherService){
        this.publisherService= publisherService;
    }

    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    @GetMapping
    public List<PublisherShortDTO> getAll(){
        return publisherService.getAll();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/publisher/{publisherID}")
    public PublisherShortDTO getPublisherById(@PathVariable("publisherID") long id)
    {
        return publisherService.findById(id);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/publisher/name/{publisherName}")
    public List<PublisherShortDTO> getPublisherByName(@PathVariable("publisherName") String name) {
        return publisherService.getByNameContaining(name);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/publisher/{publisherID}")
    public void deletePublisherById(@PathVariable("publisherID") long id)
    {
        publisherService.delete(id);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/publisher")
    public PublisherShortDTO savePublisher(@RequestBody PublisherShortDTO publisherShortDTO)
    {
        return publisherService.create(publisherShortDTO);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/publisher")
    public void updatePublisher(@RequestBody PublisherDTO publisherDTO)    {
        publisherService.update(publisherDTO);
    }


}