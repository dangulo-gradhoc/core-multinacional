package com.multinacional.core.model.service;

import com.multinacional.core.api.dto.empleado.EmpleadoInputDto;
import com.multinacional.core.api.dto.empleadodep.EmpleadoDepInputDto;
import com.multinacional.core.api.dto.empleadodep.EmpleadoDepMinOutputDto;
import com.multinacional.core.api.dto.empleadodep.EmpleadoDepOutputDto;
import com.multinacional.core.api.service.IEmpleadoDepService;
import com.multinacional.core.model.entity.Departamento;
import com.multinacional.core.model.entity.EmpleadoDep;
import com.multinacional.core.model.mapper.EmpleadoDepMapper;
import com.multinacional.core.model.repositoryJpa.IDepartamentoDAO;
import com.multinacional.core.model.repositoryJpa.IEmpleadoDAO;
import com.multinacional.core.model.repositoryJpa.IEmpleadoDepDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmpleadoDepService implements IEmpleadoDepService {

    private final IEmpleadoDepDAO empleadoDepDAO;
    private final IEmpleadoDAO empleadoDAO;
    private final IDepartamentoDAO departamentoDAO;
    private final EmpleadoDepMapper empleadoDepMapper;

    @Override
    public List<EmpleadoDepOutputDto> findAll() {
        List<EmpleadoDep> entities = empleadoDepDAO.findAll();
        return empleadoDepMapper.convertToEmpleadoDepOutputDtoList(entities);
    }

    @Override
    public EmpleadoDepOutputDto create(EmpleadoDepInputDto inputDto) throws RuntimeException {
        if(inputDto.getId() != null){
            throw new IllegalArgumentException("La empresa ya existe");
        }
        if(inputDto.getCargo()==null){
            throw new IllegalArgumentException("El cargo no puede ser null");
        }
        final  EmpleadoDep  empleadoDep= new EmpleadoDep();
        BeanUtils.copyProperties(inputDto, empleadoDep,"codEmpleado","codDepartamento");
        empleadoDep.setEmpleado(empleadoDAO.findById(inputDto.getCodEmpleado()).orElseThrow(() -> new EntityNotFoundException()));
        empleadoDep.setDepartamento(departamentoDAO.findById(inputDto.getCodDepartamento()).orElseThrow(()->new EntityNotFoundException()));

        return empleadoDepMapper.convertToEmpleadoDepOutputDto(empleadoDepDAO.save(empleadoDep));
    }

    @Override
    public EmpleadoDepOutputDto findByEmpleadoDep(Long idEmployableDep) {
        Optional<EmpleadoDep> opEmployableDep = empleadoDepDAO.findById(idEmployableDep);
        EmpleadoDepOutputDto employableDepOutDto = new EmpleadoDepOutputDto();
        if (opEmployableDep.isPresent()) {
            BeanUtils.copyProperties(opEmployableDep.get(), employableDepOutDto);
        }
        return employableDepOutDto;
    }

    @Override
    public EmpleadoDepMinOutputDto findMinByEmpleadoDep(Long idEmployableDep) {
        Optional<EmpleadoDep> opEmployableDep = empleadoDepDAO.findById(idEmployableDep);
        EmpleadoDepMinOutputDto employableDepMinOutDto = new EmpleadoDepMinOutputDto();
        if (opEmployableDep.isPresent()) {
            BeanUtils.copyProperties(opEmployableDep.get(), employableDepMinOutDto);
        }
        return employableDepMinOutDto;
    }
}
