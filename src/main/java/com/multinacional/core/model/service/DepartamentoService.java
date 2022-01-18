package com.multinacional.core.model.service;

import java.util.List;
import java.util.Optional;

import com.multinacional.core.api.dto.departamento.DepartamentoOutputMinDto;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.multinacional.core.api.service.IDepartamentoService;
import com.multinacional.core.model.entity.Departamento;
import com.multinacional.core.model.mapper.DepartamentoMapper;
import com.multinacional.core.model.repositoryJpa.IDepartamentoDAO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DepartamentoService implements IDepartamentoService {

    private final IDepartamentoDAO IDepartamentoDAO;

    private final DepartamentoMapper departamentoMapper;

    @Override
    public List<DepartamentoOutputMinDto> findAll() {
        List<Departamento> entities = IDepartamentoDAO.findAll();
        return departamentoMapper.convertToDepartamentoOutputDtoList(entities);
    }

    @Override
    public DepartamentoOutputMinDto findByDepartamento(Long id) {
        Optional<Departamento> opDepartment = IDepartamentoDAO.findById(id);
        DepartamentoOutputMinDto departmentOutDto = new DepartamentoOutputMinDto();
        if (opDepartment.isPresent()) {
            BeanUtils.copyProperties(opDepartment.get(), departmentOutDto);
        }

        return departmentOutDto;
    }

}