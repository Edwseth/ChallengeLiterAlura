package com.edwinserrano.ChallengeLiterAlura.repository;

import com.edwinserrano.ChallengeLiterAlura.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
  List<Book> findByLanguage(String language);
  Long countByLanguage(String language);
  List<Book> findTop10ByOrderByDownloadCountDesc();

  @Query("SELECT DISTINCT b.language FROM Book b")
  List<String> findDistinctLanguages();
}
