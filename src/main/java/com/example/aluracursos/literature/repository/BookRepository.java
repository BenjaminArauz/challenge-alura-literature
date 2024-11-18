package com.example.aluracursos.literature.repository;

import com.example.aluracursos.literature.models.model.Author;
import com.example.aluracursos.literature.models.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findByTitle(String title);

    @Query("SELECT DISTINCT unnest(languages) FROM Book")
    List<String> findLanguages();

    @Query(value = "SELECT * FROM book b WHERE :language = ANY(b.languages)", nativeQuery = true)
    List<Book> findByLanguagesContaining(String language);
}
