package com.company.FinalProject.repo;
import com.company.FinalProject.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByNameIsContainingIgnoreCase(String name);
    @Query(value="""
            SELECT b.*
            FROM book b,
                 book_genre bg,
                 genre g
            WHERE b.book_id = bg.book_id
              and bg.genre_id = g.genre_id
              and g.name = :genreName
""",nativeQuery = true)
    List<Book> findAllByGenre(String genreName);
}
