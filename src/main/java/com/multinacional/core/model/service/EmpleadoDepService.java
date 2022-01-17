package com.multinacional.core.model.service;

import com.multinacional.core.api.dto.empleado.EmpleadoOutputDto;
import com.multinacional.core.api.dto.empleadodep.EmpleadoDepMinOutputDto;
import com.multinacional.core.api.dto.empleadodep.EmpleadoDepOutputDto;
import com.multinacional.core.api.service.IEmpleadoDepService;
import com.multinacional.core.model.entity.Empleado;
import com.multinacional.core.model.entity.EmpleadoDep;
import com.multinacional.core.model.mapper.EmpleadoDepMapper;
import com.multinacional.core.model.repository.EmpleadoDepDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmpleadoDepService implements IEmpleadoDepService {

    private final EmpleadoDepDAO empleadoDepDAO;
    private final EmpleadoDepMapper empleadoDepMapper;

    @Override
    public List<EmpleadoDepOutputDto> findAll() {
        List<EmpleadoDep> entities = empleadoDepDAO.findAll();
        return empleadoDepMapper.convertToEmpleadoDepOutputDtoList(entities);
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
