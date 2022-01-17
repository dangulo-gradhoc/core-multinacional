package com.multinacional.core.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.multinacional.core.model.entity.Departamento;


@Repository
public interface DepartamentoDAO extends JpaRepository<Departamento, Long> {

    Optional<Departamento> findById(Long id);
}