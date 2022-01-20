package com.multinacional.core.model.service;

import com.multinacional.core.api.dto.departamento.DepartamentoMinInputDto;
import com.multinacional.core.api.dto.departamento.DepartamentoMinOutputDto;
import com.multinacional.core.api.dto.generic.ListaGenericDto;
import com.multinacional.core.api.service.IDepartamentoService;
import com.multinacional.core.model.entity.Departamento;
import com.multinacional.core.model.mapper.DepartamentoMapper;
import com.multinacional.core.model.repositoryJdbc.JdbcDepartamentoRepositoryImpl;
import com.multinacional.core.model.repositoryJpa.IDepartamentoDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class DepartamentoService implements IDepartamentoService {

    private final IDepartamentoDAO departamentoDAO;

    private final JdbcDepartamentoRepositoryImpl jdbcDepartamentoRepository;

    private final DepartamentoMapper departamentoMapper;

    @Override
    public List<DepartamentoMinOutputDto> findAll() {
        List<Departamento> entities = departamentoDAO.findAll();
        return departamentoMapper.convertToDepartamentoOutputDtoList(entities);
    }

    @Override
    @Transactional
    public DepartamentoMinOutputDto create(DepartamentoMinInputDto inputDto) throws IllegalArgumentException{
        if(inputDto.getId() != null){
            throw new IllegalArgumentException("El departamento ya existe");
        }
        if(inputDto.getNombre()==null){
            throw new IllegalArgumentException("El nombre no puede ser null");
        }
        final Departamento departamento=new Departamento();
        BeanUtils.copyProperties(inputDto, departamento, "Set<Empresas>","Set<EmpleadoDep>");

        return departamentoMapper.convertToDepartamentoOutputDto(departamentoDAO.save(departamento));
    }

    @Override
    public DepartamentoMinOutputDto update(DepartamentoMinInputDto inputDto) throws IllegalArgumentException{
        if(inputDto.getId() == null){
            throw new IllegalArgumentException("El departamento no puede ser null ");
        }
        final Departamento departamento= departamentoDAO.findById(inputDto.getId()).orElseThrow(()
                -> new EntityNotFoundException());

        BeanUtils.copyProperties(inputDto, departamento);
        return departamentoMapper.convertToDepartamentoOutputDto(departamentoDAO.save(departamento));
    }

    @Override
    public DepartamentoMinOutputDto findByDepartamento(Long id) {
        Optional<Departamento> opDepartment = departamentoDAO.findById(id);
        DepartamentoMinOutputDto departmentOutDto = new DepartamentoMinOutputDto();
        if (opDepartment.isPresent()) {
            BeanUtils.copyProperties(opDepartment.get(), departmentOutDto);
        }

        return departmentOutDto;
    }

    @Override
    public ListaGenericDto<DepartamentoMinOutputDto> findAllDepartamentosByEmpresas(Long idEmpresa, Optional<Integer> pageNo, Optional<Integer> pageSize) {
        log.info("Solicitando Empresas el nombre de tipo -> {}", idEmpresa);
        Pageable paging = PageRequest.of(pageNo.orElse(0), pageSize.orElse(Integer.MAX_VALUE),
                Sort.by("id").ascending());

        Page<DepartamentoMinOutputDto> departamentoPage = jdbcDepartamentoRepository.findAllDepartamentoByEmpresa(idEmpresa, paging);
        ListaGenericDto<DepartamentoMinOutputDto> departamentosMin = new ListaGenericDto<>();
        departamentosMin.setTotal(departamentoPage.getTotalElements());
        departamentosMin.setLista(departamentoPage.getContent());

        return departamentosMin;
    }

}