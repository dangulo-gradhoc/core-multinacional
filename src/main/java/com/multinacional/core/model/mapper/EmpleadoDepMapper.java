package com.multinacional.core.model.mapper;

import com.multinacional.core.api.dto.empleadodep.EmpleadoDepOutputDto;
import com.multinacional.core.model.entity.EmpleadoDep;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EmpleadoDepMapper {

    List<EmpleadoDepOutputDto> convertToEmpleadoDepOutputDtoList(List<EmpleadoDep> entities);

}