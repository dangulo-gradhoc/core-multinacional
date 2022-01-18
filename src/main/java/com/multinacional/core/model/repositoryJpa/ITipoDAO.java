package com.multinacional.core.model.repositoryJpa;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.multinacional.core.model.entity.Tipo;


@Repository
public interface ITipoDAO extends JpaRepository<Tipo, Long> {

    Optional<Tipo> findById(Long id);
}