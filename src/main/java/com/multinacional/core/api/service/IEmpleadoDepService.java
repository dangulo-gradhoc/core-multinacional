package com.multinacional.core.api.service;

import com.multinacional.core.api.dto.empleadodep.EmpleadoDepInputDto;
import com.multinacional.core.api.dto.empleadodep.EmpleadoDepMinOutputDto;
import com.multinacional.core.api.dto.empleadodep.EmpleadoDepOutputDto;

import java.util.List;

public interface IEmpleadoDepService {

    List<EmpleadoDepOutputDto> findAll();

    EmpleadoDepOutputDto create(EmpleadoDepInputDto inputDto);

    EmpleadoDepOutputDto findByEmpleadoDep(Long idEmployableDep);

    EmpleadoDepMinOutputDto findMinByEmpleadoDep(Long idEmployableDep);
}
