package com.multinacional.core.model.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.multinacional.core.api.dto.departamento.DepartamentoOutputDto;
import com.multinacional.core.api.service.IDepartamentoService;
import com.multinacional.core.model.entity.Departamento;
import com.multinacional.core.model.mapper.DepartamentoMapper;
import com.multinacional.core.model.repository.DepartamentoDAO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DepartamentoService implements IDepartamentoService {

    private final DepartamentoDAO departamentoDAO;

    private final DepartamentoMapper departamentoMapper;

    @Override
    public List<DepartamentoOutputDto> findAll() {
        List<Departamento> entities = departamentoDAO.findAll();
        return departamentoMapper.convertToDepartamentoOutputDtoList(entities);
    }

    @Override
    public DepartamentoOutputDto findByDepartamento(Long id) {
        Optional<Departamento> opDepartment = departamentoDAO.findById(id);
        DepartamentoOutputDto departmentOutDto = new DepartamentoOutputDto();
        if (opDepartment.isPresent()) {
            BeanUtils.copyProperties(opDepartment.get(), departmentOutDto);
        }

        return departmentOutDto;
    }

}