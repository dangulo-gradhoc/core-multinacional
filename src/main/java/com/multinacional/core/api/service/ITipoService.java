package com.multinacional.core.api.service;

import com.multinacional.core.api.dto.tipo.TipoMinInputDto;
import com.multinacional.core.api.dto.tipo.TipoMinOutputDto;
import com.multinacional.core.model.entity.Tipo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITipoService {

    List<TipoMinOutputDto> findAll();

    TipoMinOutputDto create(TipoMinInputDto inputDto);

    TipoMinOutputDto update(TipoMinInputDto inputDto);

    TipoMinOutputDto findByTipo(Long idTipo);

}