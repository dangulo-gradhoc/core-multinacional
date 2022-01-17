package com.multinacional.core.model.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.multinacional.core.api.dto.departamento.DepartamentoOutputDto;
import com.multinacional.core.model.entity.Departamento;

@Mapper(componentModel = "spring")
public interface DepartamentoMapper {

    List<DepartamentoOutputDto> convertToDepartamentoOutputDtoList(List<Departamento> entities);

}