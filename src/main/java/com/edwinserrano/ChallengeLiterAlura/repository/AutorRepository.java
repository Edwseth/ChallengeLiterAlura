package com.edwinserrano.ChallengeLiterAlura.repository;

import com.edwinserrano.ChallengeLiterAlura.model.Autores;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AutorRepository extends JpaRepository<Autores, Long> {
Autores findByNombre(String nombre);
    //List<Autores> findByBirthYearLessThanEqualAndDeathYearGreaterThanEqualOrDeathYearIsNull(int birthYear, int deathYear);
}
