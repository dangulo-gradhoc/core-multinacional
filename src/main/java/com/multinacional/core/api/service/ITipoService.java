package com.multinacional.core.api.service;

import com.multinacional.core.api.dto.tipo.TipoMinInputDto;
import com.multinacional.core.api.dto.tipo.TipoMinOutputDto;
import com.multinacional.core.model.entity.Tipo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITipoService {

    List<TipoMinOutputDto> findAll();

    List<TipoMinOutputDto> findByNombre(String nombre);

    TipoMinOutputDto create(TipoMinInputDto inputDto);

    TipoMinOutputDto update(TipoMinInputDto inputDto);

    Boolean delete(Long id);

    Boolean deleteAll();

    TipoMinOutputDto findByTipo(Long idTipo);

}