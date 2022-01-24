package com.multinacional.core.model.mapper;

import com.multinacional.core.api.dto.departamento.DepartamentoMinOutputDto;
import com.multinacional.core.model.entity.Departamento;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DepartamentoMapper {

    List<DepartamentoMinOutputDto> convertToDepartamentoOutputDtoList(List<Departamento> entities);

    DepartamentoMinOutputDto convertToDepartamentoMinOutputDto(Departamento entity);
}