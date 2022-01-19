package com.multinacional.core.api.service;

import java.util.List;
import java.util.Optional;

import com.multinacional.core.api.dto.departamento.DepartamentoMinOutputDto;
import com.multinacional.core.api.dto.generic.ListaGenericDto;
import org.springframework.stereotype.Repository;

@Repository
public interface IDepartamentoService {

    List<DepartamentoMinOutputDto> findAll();

    DepartamentoMinOutputDto findByDepartamento(Long idDepartamento);

}