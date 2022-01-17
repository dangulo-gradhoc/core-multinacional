package com.multinacional.core.api.service;

import com.multinacional.core.api.dto.empleado.EmpleadoMinOutputDto;
import com.multinacional.core.api.dto.empleado.EmpleadoOutputDto;

import java.util.List;

public interface IEmpleadoService {
    List<EmpleadoOutputDto> findAll();

    EmpleadoOutputDto findByEmpleado(Long idEmpleado);

    EmpleadoMinOutputDto findMinByEmpleado(Long idEmpleado);
}
