package com.multinacional.core.model.service;

import com.multinacional.core.api.dto.empresa.EmpresaInputDto;
import com.multinacional.core.api.dto.empresa.EmpresaMinOutputDto;
import com.multinacional.core.api.dto.empresa.EmpresaOutputDto;
import com.multinacional.core.api.dto.generic.ListaGenericDto;
import com.multinacional.core.api.service.IEmpresaService;
import com.multinacional.core.model.entity.Departamento;
import com.multinacional.core.model.entity.Empresa;
import com.multinacional.core.model.mapper.EmpresaMapper;
import com.multinacional.core.model.repositoryJdbc.IJdbcEmpresaRepository;
import com.multinacional.core.model.repositoryJpa.IDepartamentoDAO;
import com.multinacional.core.model.repositoryJpa.IEmpresaDAO;
import com.multinacional.core.model.repositoryJpa.ITipoDAO;
import com.multinacional.core.model.service.entitymanager.MultinacionalEntityManagerService;
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
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmpresaService extends MultinacionalEntityManagerService implements IEmpresaService {

    private final IEmpresaDAO empresaDAO;
    private final ITipoDAO tipoDao;
    private final IDepartamentoDAO departamentoDAO;
    private final IJdbcEmpresaRepository jdbcEmpresaRepository;

    private final EmpresaMapper empresaMapper;

    @Override
    public List<EmpresaOutputDto> findAll() {
        List<Empresa> entities = empresaDAO.findAll();
        return empresaMapper.convertToEmpresaOutputDtoList(entities);
    }

    @Override
    public List<EmpresaOutputDto> findByNombre(String nombre) {
        List<Empresa> entidades = empresaDAO.findAllByNombre(nombre);
        List<EmpresaOutputDto> listaFinal = empresaMapper.convertToEmpresaOutputDtoList(entidades);
        return listaFinal;
    }

    @Override
    @Transactional
    public EmpresaOutputDto create(EmpresaInputDto inputDto) throws IllegalArgumentException {
        if (inputDto.getId() != null) {
            throw new IllegalArgumentException("La empresa ya existe");
        }
        if (inputDto.getNombre().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede ser null");
        }
        final Empresa empresa = new Empresa();
        BeanUtils.copyProperties(inputDto, empresa, "codTipo", "codsDepartamentos");

        empresa.setTipo(tipoDao.findById(inputDto.getCodTipo()).get());

        empresa.setListaDepartamento(inputDto.getCodsDepartamentos().stream().map(
                dep -> departamentoDAO.findById(dep).orElseThrow(() -> new EntityNotFoundException())).collect(Collectors.toSet()));

        return empresaMapper.convertToEmpresaOutputDto(empresaDAO.save(empresa));
    }

    @Override
    @Transactional
    public EmpresaOutputDto update(EmpresaInputDto inputDto) throws RuntimeException {
        if (inputDto.getId() == null) {
            throw new IllegalArgumentException("El id no puede ser null ");
        }

        final Empresa empresa = empresaDAO.findById(inputDto.getId()).orElseThrow(()
                -> new EntityNotFoundException());


        BeanUtils.copyProperties(inputDto, empresa, "codsDepartamentos");
        empresa.setTipo(tipoDao.findById(inputDto.getCodTipo()).get());
        proccessEmpresaDepOnUpdate(empresa, inputDto.getCodsDepartamentos());
        return empresaMapper.convertToEmpresaOutputDto(empresaDAO.save(empresa));
    }


    public void proccessEmpresaDepOnUpdate(final Empresa empresa, final Set<Long> codsDepartamentos) {

        if (codsDepartamentos != null && !codsDepartamentos.isEmpty()) {
            final Set<Departamento> departamentosNuevos = new HashSet<>();

            for (final Long id : codsDepartamentos) {
                departamentosNuevos.add(entityManager.getReference(Departamento.class, id));
            }
            final Set<Long> departamentosBorrar = new HashSet<>();
            for (final Departamento agrupacionDep : empresa.getListaDepartamento()) {

                if (!departamentosNuevos.contains(agrupacionDep)) {
                    departamentosBorrar.add(agrupacionDep.getId());
                }
            }
            if (!departamentosBorrar.isEmpty()) {
                jdbcEmpresaRepository.deleteDepartamentos(empresa.getId(), departamentosBorrar);
            }

            empresa.addDepartamento(departamentosNuevos);
        } else {
            jdbcEmpresaRepository.deleteAllDepartamento(empresa.getId());
        }

    }

    @Override
    public Boolean delete(final Long id) throws RuntimeException {
        Empresa empresa = empresaDAO.findById(id)
                .orElseThrow(() -> new EntityNotFoundException());
        empresaDAO.delete(empresa);
        return Boolean.TRUE;
    }

    @Override
    public Boolean deleteAll() {
        empresaDAO.deleteAll();
        return true;
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
                                                                         Optional<Integer> pageSize) {
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
