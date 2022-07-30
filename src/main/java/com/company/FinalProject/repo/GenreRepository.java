package com.company.FinalProject.repo;

import com.company.FinalProject.entity.Book;
import com.company.FinalProject.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {
    List<Genre> findByNameIsContainingIgnoreCase(String name);
}
