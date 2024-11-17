package com.edwinserrano.ChallengeLiterAlura.repository;

import com.edwinserrano.ChallengeLiterAlura.model.Libros;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LibroRepository extends JpaRepository <Libros, Long> {
//    List<Libros> findByLanguage(String language);
//    Long countByLanguage(String language);
//    List<Libros> findTop10ByOrderByDownloadCountDesc();
//
//    @Query("SELECT DISTINCT b.language FROM Book b")
//    List<String> findDistinctLanguages();
}
