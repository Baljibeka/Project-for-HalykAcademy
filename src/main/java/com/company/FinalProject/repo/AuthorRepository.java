package com.company.FinalProject.repo;

import com.company.FinalProject.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    @Query(value = """ 
            select * 
            from author a where lower(a.name) like lower(:name)
            or lower(a.surname) like lower(:surname) 
             or lower(a.patronymic) like lower(:lastname)
             """, nativeQuery = true)
    List<Author> findByFIO(String name, String surname, String lastname);

    @Query(value = """
                        SELECT *
                        FROM author a ,
                        authors_book ab,
                        book_genre bg,
                        genre g
                        WHERE a.author_id = ab.author_id
                                                and bg.genre_id= g.genre_id
                                                and ab.book_id= bg.book_id
                                                and g.name in :genreList
            """, nativeQuery = true)
    List<Author> findAllByGenresListIsContainingIgnoreCase(List<String> genreList);
}
