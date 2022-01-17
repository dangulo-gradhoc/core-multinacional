package com.multinacional.core.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.multinacional.core.model.entity.Tipo;


@Repository
public interface TipoDAO extends JpaRepository<Tipo, Long> {

    Optional<Tipo> findById(Long id);
}