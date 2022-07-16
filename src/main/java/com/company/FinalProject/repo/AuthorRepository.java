package com.company.FinalProject.repo;

import com.company.FinalProject.dto.AuthorDTO;
import org.springframework.data.repository.CrudRepository;

public interface AuthorRepository extends CrudRepository<AuthorDTO, Long> {
}
