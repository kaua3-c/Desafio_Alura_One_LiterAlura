package com.literalura.repository;

import com.literalura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AutorRepository extends JpaRepository<Autor, Long> {
    Optional<Autor> findByNomeIgnoreCase(String nome);
    @Query("SELECT a FROM Autor a WHERE a.dataNascimento <= :ano AND (a.dataFalecimento >= :ano OR a.dataFalecimento IS NULL)")
    List<Autor> autoresVivosNoAno(Integer ano);

}
