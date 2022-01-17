package com.multinacional.core.model.mapper;

import com.multinacional.core.api.dto.empleado.EmpleadoOutputDto;
import com.multinacional.core.model.entity.Empleado;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EmpleadoMapper {

    List<EmpleadoOutputDto> convertToEmpleadoOutputDtoList(List<Empleado> entities);

}