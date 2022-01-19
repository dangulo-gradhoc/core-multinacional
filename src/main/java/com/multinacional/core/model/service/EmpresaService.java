package com.multinacional.core.model.service;

import java.util.List;
import java.util.Optional;

import com.multinacional.core.api.dto.generic.ListaGenericDto;
import com.multinacional.core.model.repositoryJdbc.IJdbcEmpresaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.multinacional.core.api.dto.empresa.EmpresaMinOutputDto;
import com.multinacional.core.api.dto.empresa.EmpresaOutputDto;
import com.multinacional.core.api.service.IEmpresaService;
import com.multinacional.core.model.entity.Empresa;
import com.multinacional.core.model.mapper.EmpresaMapper;
import com.multinacional.core.model.repositoryJpa.IEmpresaDAO;

import lombok.RequiredArgsConstructor;
@Slf4j
@Service
@RequiredArgsConstructor
public class EmpresaService implements IEmpresaService {

    private final IEmpresaDAO empresaDAO;

    private final IJdbcEmpresaRepository jdbcEmpresaRepository;

    private final EmpresaMapper empresaMapper;

    @Override
    public List<EmpresaOutputDto> findAll() {
        List<Empresa> entities = empresaDAO.findAll();
        return empresaMapper.convertToEmpresaOutputDtoList(entities);
    }

    @Override
    public EmpresaOutputDto findByEmpresa(Long id) {
        Optional<Empresa> opEmpresa = empresaDAO.findById(id);
        EmpresaOutputDto empresaOutDto = new EmpresaOutputDto();
        if (opEmpresa.isPresent()) {
            BeanUtils.copyProperties(opEmpresa.get(), empresaOutDto);
        }
        return empresaOutDto;
    }

    @Override
    public EmpresaMinOutputDto findMinByEmpresa(Long id) {
        Optional<Empresa> opEmpresa = empresaDAO.findById(id);
        EmpresaMinOutputDto empresaMinOutDto = new EmpresaMinOutputDto();
        if (opEmpresa.isPresent()) {
            BeanUtils.copyProperties(opEmpresa.get(), empresaMinOutDto);
        }
        return empresaMinOutDto;
    }

    @Override
    public ListaGenericDto<EmpresaMinOutputDto> findAllEmpresasMinByTipo(String nombreTipo, Optional<Integer> pageNo,
                                                                         Optional<Integer> pageSize)
    {
        log.info("Solicitando Empresas el nombre de tipo -> {}", nombreTipo);
        Pageable paging = PageRequest.of(pageNo.orElse(0), pageSize.orElse(Integer.MAX_VALUE),
                Sort.by("nombre").ascending());

        Page<EmpresaMinOutputDto> empresaPage = jdbcEmpresaRepository.findAllEmpresasMinByTipo(nombreTipo, paging);
        ListaGenericDto<EmpresaMinOutputDto> empresasMin = new ListaGenericDto<>();
        empresasMin.setTotal(empresaPage.getTotalElements());
        empresasMin.setLista(empresaPage.getContent());

        return empresasMin;
    }

}
