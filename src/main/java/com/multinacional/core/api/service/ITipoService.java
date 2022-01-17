package com.multinacional.core.api.service;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.multinacional.core.api.dto.tipo.TipoOutputDto;

@Repository
public interface ITipoService {

    List<TipoOutputDto> findAll();

    TipoOutputDto findByTipo(Long idTipo);

}