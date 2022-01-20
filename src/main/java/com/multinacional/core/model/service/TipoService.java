package com.multinacional.core.model.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.multinacional.core.api.dto.tipo.TipoMinInputDto;
import com.multinacional.core.api.dto.tipo.TipoMinOutputDto;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.multinacional.core.api.service.ITipoService;
import com.multinacional.core.model.entity.Tipo;
import com.multinacional.core.model.mapper.TipoMapper;
import com.multinacional.core.model.repositoryJpa.ITipoDAO;

import lombok.RequiredArgsConstructor;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
public class TipoService implements ITipoService {

    private final ITipoDAO tipoDAO;

    private final TipoMapper tipoMapper;

    @Override
    public List<TipoMinOutputDto> findAll() {
        List<Tipo> entidades = new ArrayList<>();
        entidades = tipoDAO.findAll();
        List<TipoMinOutputDto> listaFinal = tipoMapper.convertToTipoMinOutputDtoList(entidades);
        return listaFinal;
    }

    @Override
    public TipoMinOutputDto create(TipoMinInputDto inputDto) throws IllegalArgumentException{

        if(inputDto.getId() != null){
            throw new IllegalArgumentException("El tipo de empresa ya existe");
        }
        if (inputDto.getNombre().isEmpty()){
            throw new IllegalArgumentException("El nombre no puede ser null");
        }
        final Tipo tipo=new Tipo();
        BeanUtils.copyProperties(inputDto, tipo);

        return tipoMapper.convertToTipoMinOutputDto(tipoDAO.save(tipo));
    }

    @Override
    public TipoMinOutputDto update(TipoMinInputDto inputDto) {
        if(inputDto.getId() == null){
            throw new IllegalArgumentException("El empleado no puede ser null ");
        }
        final Tipo tipo = tipoDAO.findById(inputDto.getId()).orElseThrow(()
                ->new EntityNotFoundException());

        BeanUtils.copyProperties(inputDto, tipo);
        return tipoMapper.convertToTipoMinOutputDto(tipoDAO.save(tipo));
    }

    @Override
    public TipoMinOutputDto findByTipo(Long id) {

        Optional<Tipo> opTipo = tipoDAO.findById(id);
        TipoMinOutputDto tipoMinOutputDto = new TipoMinOutputDto();
        if (opTipo.isPresent()) {
            BeanUtils.copyProperties(opTipo.get(), tipoMinOutputDto);
        }

        return tipoMinOutputDto;
    }

}