package com.multinacional.core.model.mapper;

import java.util.List;

import com.multinacional.core.api.dto.departamento.DepartamentoOutputMinDto;
import org.mapstruct.Mapper;

import com.multinacional.core.model.entity.Departamento;

@Mapper(componentModel = "spring")
public interface DepartamentoMapper {

    List<DepartamentoOutputMinDto> convertToDepartamentoOutputDtoList(List<Departamento> entities);

}