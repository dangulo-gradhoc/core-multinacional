package com.multinacional.core.model.service;

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
import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class TipoService implements ITipoService {

    private final ITipoDAO tipoDAO;

    private final TipoMapper tipoMapper;

    @Override
    public List<TipoMinOutputDto> findAll() {
        List<Tipo> entidades = tipoDAO.findAll();
        List<TipoMinOutputDto> listaFinal = tipoMapper.convertToTipoMinOutputDtoList(entidades);
        return listaFinal;
    }



    @Override
    public List<TipoMinOutputDto> findByNombre(String nombre)  throws  IllegalArgumentException{
        List<Tipo> entidades = tipoDAO.findAllByNombre(nombre);
        List<TipoMinOutputDto> listaFinal= tipoMapper.convertToTipoMinOutputDtoList(entidades);
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
    @Transactional
    public TipoMinOutputDto update(TipoMinInputDto inputDto) {
        if(inputDto.getId() == null){
            throw new IllegalArgumentException("El id no puede ser null ");
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

    @Override
    public Boolean delete(final Long id) throws RuntimeException{
        Tipo tipo = tipoDAO.findById(id)
                .orElseThrow(() ->new EntityNotFoundException());
        tipoDAO.delete(tipo);
        return Boolean.TRUE;
    }

    @Override
    public Boolean deleteAll() {
        tipoDAO.deleteAll();
        return true;
    }
}