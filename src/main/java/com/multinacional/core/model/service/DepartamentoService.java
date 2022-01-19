package com.multinacional.core.model.service;

import java.util.List;
import java.util.Optional;

import com.multinacional.core.api.dto.departamento.DepartamentoMinOutputDto;
import com.multinacional.core.api.dto.empresadep.EmpresaDepMinOutputDto;
import com.multinacional.core.api.dto.generic.ListaGenericDto;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    private final IJdbcEmpresaDepRepository jdbcEmpresaDepRepository;

    private final DepartamentoMapper departamentoMapper;

    @Override
    public List<DepartamentoMinOutputDto> findAll() {
        List<Departamento> entities = IDepartamentoDAO.findAll();
        return departamentoMapper.convertToDepartamentoOutputDtoList(entities);
    }

    @Override
    public DepartamentoMinOutputDto findByDepartamento(Long id) {
        Optional<Departamento> opDepartment = IDepartamentoDAO.findById(id);
        DepartamentoMinOutputDto departmentOutDto = new DepartamentoMinOutputDto();
        if (opDepartment.isPresent()) {
            BeanUtils.copyProperties(opDepartment.get(), departmentOutDto);
        }

        return departmentOutDto;
    }

    @Override
    public ListaGenericDto<EmpresaDepMinOutputDto> findAll(Optional<Integer> pageNo, Optional<Integer> pageSize) {

        Pageable paging = PageRequest.of(pageNo.orElse(0), pageSize.orElse(Integer.MAX_VALUE));

        Page<EmpresaDepMinOutputDto> empresaPage = jdbcEmpresaDepRepository.findAll(paging);
        ListaGenericDto<EmpresaDepMinOutputDto> empresasdepMin= new ListaGenericDto<>();
        empresasdepMin.setTotal(empresaPage.getTotalElements());
        empresasdepMin.setLista(empresaPage.getContent());

        return empresasdepMin;
    }


}