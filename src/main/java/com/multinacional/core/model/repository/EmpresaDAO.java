package com.multinacional.core.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.multinacional.core.model.entity.Empresa;


@Repository
public interface EmpresaDAO extends JpaRepository<Empresa, Long> {

//    Optional<Empresa> findById(Long id);
}
