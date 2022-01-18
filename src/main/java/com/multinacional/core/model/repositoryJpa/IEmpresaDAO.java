package com.multinacional.core.model.repositoryJpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.multinacional.core.model.entity.Empresa;


@Repository
public interface IEmpresaDAO extends JpaRepository<Empresa, Long> {

//    Optional<Empresa> findById(Long id);
}
