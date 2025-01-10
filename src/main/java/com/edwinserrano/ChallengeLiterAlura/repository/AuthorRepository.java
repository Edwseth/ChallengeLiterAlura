package com.edwinserrano.ChallengeLiterAlura.repository;

import com.edwinserrano.ChallengeLiterAlura.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuthorRepository extends JpaRepository<Author, Long> {
  List<Author> findByBirthYearLessThanEqualAndDeathYearGreaterThanEqualOrDeathYearIsNull(int birthYear, int deathYear);
}
