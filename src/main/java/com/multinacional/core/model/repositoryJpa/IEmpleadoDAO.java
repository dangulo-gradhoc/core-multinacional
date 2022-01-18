package com.multinacional.core.model.repositoryJpa;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.multinacional.core.model.entity.Empleado;


@Repository
public interface IEmpleadoDAO extends JpaRepository<Empleado, Long> {

    Optional<Empleado> findById(Long id);
}