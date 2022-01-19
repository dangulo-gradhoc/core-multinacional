package com.multinacional.core.api.service;

import com.multinacional.core.api.dto.departamento.DepartamentoInputDto;
import com.multinacional.core.api.dto.departamento.DepartamentoMinOutputDto;
import com.multinacional.core.api.dto.generic.ListaGenericDto;
import com.multinacional.core.model.entity.Departamento;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IDepartamentoService {

    List<DepartamentoMinOutputDto> findAll();

    DepartamentoMinOutputDto create(DepartamentoInputDto departamentoInputDto);

    DepartamentoMinOutputDto findByDepartamento(Long idDepartamento);

    ListaGenericDto<DepartamentoMinOutputDto> findAllDepartamentosByEmpresas(Long idEmpresa, Optional<Integer> pageNo, Optional<Integer> pageSize);
}