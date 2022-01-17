package com.multinacional.core.model.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.multinacional.core.api.dto.tipo.TipoOutputDto;
import com.multinacional.core.model.entity.Tipo;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TipoMapper {

    List<TipoOutputDto> convertToTipoOutputDtoList(List<Tipo> entities);

}