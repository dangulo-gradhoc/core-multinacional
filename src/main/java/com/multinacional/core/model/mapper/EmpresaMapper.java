package com.multinacional.core.model.mapper;

import java.util.List;

import com.multinacional.core.api.dto.empresa.EmpresaMinOutputDto;
import org.mapstruct.Mapper;

import com.multinacional.core.api.dto.empresa.EmpresaOutputDto;
import com.multinacional.core.model.entity.Empresa;

@Mapper(componentModel = "spring")
public interface EmpresaMapper {

    List<EmpresaOutputDto> convertToEmpresaOutputDtoList(List<Empresa> entities);

    EmpresaOutputDto convertToEmpresaOutputDto(Empresa entity);


}