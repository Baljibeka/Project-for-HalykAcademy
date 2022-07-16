package com.company.FinalProject.repo;

import com.company.FinalProject.dto.PublisherDTO;
import org.springframework.data.repository.CrudRepository;

public interface PublisherRepository extends CrudRepository<PublisherDTO, Long> {
}
