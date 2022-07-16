package com.company.FinalProject.providers;

import com.company.FinalProject.dto.PublisherDTO;

import java.util.List;

public interface PublisherProvider {
    List<PublisherDTO> getAll();
    List<PublisherDTO> findByName(String name);
}
