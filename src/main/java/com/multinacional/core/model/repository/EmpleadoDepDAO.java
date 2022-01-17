package com.multinacional.core.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.multinacional.core.model.entity.EmpleadoDep;

@Repository
public interface EmpleadoDepDAO extends JpaRepository<EmpleadoDep, Long> {

    Optional<EmpleadoDep> findById(Long id);

}