package com.multinacional.core.model.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.multinacional.core.api.dto.empresa.EmpresaMinOutputDto;
import com.multinacional.core.api.dto.empresa.EmpresaOutputDto;
import com.multinacional.core.api.service.IEmpresaService;
import com.multinacional.core.model.entity.Empresa;
import com.multinacional.core.model.mapper.EmpresaMapper;
import com.multinacional.core.model.repository.EmpresaDAO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmpresaService implements IEmpresaService {

    private final EmpresaDAO empresaDAO;

    private final EmpresaMapper empresaMapper;

    @Override
    public List<EmpresaOutputDto> findAll() {
        List<Empresa> entidades = new ArrayList<>();
        entidades = empresaDAO.findAll();
        List<EmpresaOutputDto> listaFinal = empresaMapper.convertToEmpresaOutputDtoList(entidades);
        return listaFinal;
    }

    @Override
    public EmpresaOutputDto findByEmpresaId(Long id) {
        Optional<Empresa> opEmpresa = empresaDAO.findById(id);
        EmpresaOutputDto empresaOutDto = new EmpresaOutputDto();
        if (opEmpresa.isPresent()) {
            BeanUtils.copyProperties(opEmpresa.get(), empresaOutDto);
        }
        return empresaOutDto;
    }

    @Override
    public EmpresaMinOutputDto findMinByEmpresaID(Long id) {
        Optional<Empresa> opEmpresa = empresaDAO.findById(id);
        EmpresaMinOutputDto empresaMinOutDto = new EmpresaMinOutputDto();
        if (opEmpresa.isPresent()) {
            BeanUtils.copyProperties(opEmpresa.get(), empresaMinOutDto);
        }
        return empresaMinOutDto;
    }
}
