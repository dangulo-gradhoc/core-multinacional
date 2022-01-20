package com.multinacional.core.model.mapper;

import java.util.List;

import com.multinacional.core.api.dto.tipo.TipoMinOutputDto;
import org.mapstruct.Mapper;

import com.multinacional.core.model.entity.Tipo;

@Mapper(componentModel = "spring")
public interface TipoMapper {

    List<TipoMinOutputDto> convertToTipoMinOutputDtoList(List<Tipo> entities);

    TipoMinOutputDto convertToTipoMinOutputDto(Tipo tipo);

}