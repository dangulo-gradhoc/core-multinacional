package com.multinacional.core.api.service;

import com.multinacional.core.api.dto.departamento.DepartamentoMinInputDto;
import com.multinacional.core.api.dto.departamento.DepartamentoMinOutputDto;
import com.multinacional.core.api.dto.generic.ListaGenericDto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface IDepartamentoService {

    List<DepartamentoMinOutputDto> findAll();

    DepartamentoMinOutputDto create(DepartamentoMinInputDto departamentoMinInputDto);

    DepartamentoMinOutputDto update(DepartamentoMinInputDto departamentoMinInputDto);

    Boolean delete (final Long id);

    DepartamentoMinOutputDto findByDepartamento(Long idDepartamento);

    ListaGenericDto<DepartamentoMinOutputDto> findAllDepartamentosByEmpresas(Long idEmpresa, Optional<Integer> pageNo, Optional<Integer> pageSize);
}