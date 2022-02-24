package com.multinacional.core.api.service;

import com.multinacional.core.api.dto.departamento.DepartamentoMinInputDto;
import com.multinacional.core.api.dto.departamento.DepartamentoMinOutputDto;
import com.multinacional.core.api.dto.empleado.EmpleadoOutputDto;
import com.multinacional.core.api.dto.empleadodep.EmpleadoDepOutputDto;
import com.multinacional.core.api.dto.generic.ListaGenericDto;
import com.multinacional.core.api.dto.tipo.TipoMinOutputDto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IDepartamentoService {

    List<DepartamentoMinOutputDto> findAll();

    List<DepartamentoMinOutputDto> findByNombre(String nombre);

    DepartamentoMinOutputDto create(DepartamentoMinInputDto departamentoMinInputDto);

    DepartamentoMinOutputDto update(DepartamentoMinInputDto departamentoMinInputDto);

    Boolean deleteAll();

    Boolean delete (final Long id);

    DepartamentoMinOutputDto findByDepartamento(Long idDepartamento);

    ListaGenericDto<DepartamentoMinOutputDto> findAllDepartamentosByEmpresas(Long idEmpresa, Optional<Integer> pageNo, Optional<Integer> pageSize);

    ListaGenericDto<EmpleadoDepOutputDto> findAllDepartamentosByEmpleados(Long idEmpleado, Optional<Integer> pageNo, Optional<Integer> pageSize);
}