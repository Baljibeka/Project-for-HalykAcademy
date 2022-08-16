package com.company.FinalProject.services;


import com.company.FinalProject.dto.Publisher.PublisherDTO;
import com.company.FinalProject.dto.Publisher.PublisherShortDTO;

import java.util.List;

public interface PublisherService {
    public List<PublisherShortDTO> getAll();
    public PublisherShortDTO create(PublisherShortDTO publisherShortDTO);
    public void delete(long id);
    public void update(PublisherDTO publisherDTO);
    public PublisherShortDTO findById(long id);
    List<PublisherShortDTO> getByNameContaining(String name);
}
