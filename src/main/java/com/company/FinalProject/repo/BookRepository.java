package com.company.FinalProject.repo;
import com.company.FinalProject.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByNameIsContainingIgnoreCase(@Param("name") String name);
}
