package com.company.FinalProject.repo;
import com.company.FinalProject.dto.BookDTO;
import org.springframework.data.repository.CrudRepository;


public interface BookRepository extends CrudRepository<BookDTO, Long> {
}
