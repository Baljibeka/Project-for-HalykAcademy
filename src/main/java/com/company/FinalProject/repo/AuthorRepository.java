package com.company.FinalProject.repo;

import com.company.FinalProject.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    List<Author> findByName(String name);
    @Query(value="""
            SELECT *
            FROM author b,
                 author_genre bg,
                 genre g
            WHERE b.id = bg.author_id
              and bg.genre_id = g.id
              and g.name = :genreName
""",nativeQuery = true)
    List<Author> findAllByGenre(String genreName);

}
