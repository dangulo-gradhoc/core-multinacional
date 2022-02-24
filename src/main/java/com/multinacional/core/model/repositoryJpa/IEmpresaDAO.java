package com.multinacional.core.model.repositoryJpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.multinacional.core.model.entity.Empresa;

import java.util.List;


@Repository
public interface IEmpresaDAO extends JpaRepository<Empresa, Long> {

    List<Empresa> findAllByNombre(String nombre);
}
