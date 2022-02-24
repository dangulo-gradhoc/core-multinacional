package com.multinacional.core.model.repositoryJpa;

import java.util.List;
import java.util.Optional;

import com.multinacional.core.api.dto.tipo.TipoMinOutputDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.multinacional.core.model.entity.Tipo;


@Repository
public interface ITipoDAO extends JpaRepository<Tipo, Long> {

    Tipo findByNombre(String nombre);

    List<Tipo> findAllByNombre(String nombre);

}