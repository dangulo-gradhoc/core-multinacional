package com.multinacional.core.model.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.multinacional.core.api.dto.tipo.TipoOutputDto;
import com.multinacional.core.api.service.ITipoService;
import com.multinacional.core.model.entity.Tipo;
import com.multinacional.core.model.mapper.TipoMapper;
import com.multinacional.core.model.repository.TipoDAO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TipoService implements ITipoService {

    private final TipoDAO tipoDAO;

    private final TipoMapper tipoMapper;

    @Override
    public List<TipoOutputDto> findAll() {
        List<Tipo> entidades = new ArrayList<>();
        entidades = tipoDAO.findAll();
        List<TipoOutputDto> listaFinal = tipoMapper.convertToTipoOutputDtoList(entidades);
        return listaFinal;
    }

    @Override
    public TipoOutputDto findByTipo(Long id) {

        Optional<Tipo> opTipo = tipoDAO.findById(id);
        TipoOutputDto tipoOutputDto = new TipoOutputDto();
        if (opTipo.isPresent()) {
            BeanUtils.copyProperties(opTipo.get(), tipoOutputDto);
        }

        return tipoOutputDto;
    }

}