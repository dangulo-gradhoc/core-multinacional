package com.multinacional.core.api.service;

import com.multinacional.core.api.dto.empleado.EmpleadoInputDto;
import com.multinacional.core.api.dto.empleado.EmpleadoMinOutputDto;
import com.multinacional.core.api.dto.empleado.EmpleadoOutputDto;

import java.util.List;

public interface IEmpleadoService {
    List<EmpleadoOutputDto> findAll();

    List<EmpleadoOutputDto> findByNombre(String nombre);

    EmpleadoOutputDto create(EmpleadoInputDto inputDto);

    EmpleadoOutputDto update(EmpleadoInputDto inputDto);

    Boolean deleteAll();

    Boolean delete(Long id);

    EmpleadoOutputDto findByEmpleado(Long idEmpleado);

    EmpleadoMinOutputDto findMinByEmpleado(Long idEmpleado);
}
