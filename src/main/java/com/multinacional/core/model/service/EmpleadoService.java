package com.multinacional.core.model.service;

import com.multinacional.core.api.dto.empleado.EmpleadoInputDto;
import com.multinacional.core.api.dto.empleado.EmpleadoMinOutputDto;
import com.multinacional.core.api.dto.empleado.EmpleadoOutputDto;
import com.multinacional.core.api.service.IEmpleadoService;
import com.multinacional.core.model.entity.Departamento;
import com.multinacional.core.model.entity.Empleado;
import com.multinacional.core.model.entity.EmpleadoDep;
import com.multinacional.core.model.mapper.EmpleadoMapper;
import com.multinacional.core.model.repositoryJpa.IDepartamentoDAO;
import com.multinacional.core.model.repositoryJpa.IEmpleadoDAO;
import com.multinacional.core.model.repositoryJpa.IEmpleadoDepDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmpleadoService implements IEmpleadoService {
    private final IEmpleadoDAO empleadoDAO;
    private final IDepartamentoDAO departamentoDAO;

    private final IEmpleadoDepDAO empleadoDepDAO;

    private final EmpleadoMapper empleadoMapper;

    @Override
    public List<EmpleadoOutputDto> findAll() {
        List<Empleado> entities = empleadoDAO.findAll();
        return empleadoMapper.convertToEmpleadoOutputDtoList(entities);
    }

    @Override
    @Transactional
    public EmpleadoOutputDto create(EmpleadoInputDto inputDto) throws IllegalArgumentException {
        if (inputDto.getId() != null) {
            throw new IllegalArgumentException("Le emplead@ ya existe");
        }
        if (inputDto.getDni() == null) {
            throw new IllegalArgumentException("El DNI no puede ser null");
        }
        final Empleado empleado = new Empleado();
        BeanUtils.copyProperties(inputDto, empleado, "empleadodep");
        Empleado empleadoGuardado = empleadoDAO.save(empleado);
        inputDto.getEmpleadodep().forEach(empleadodep -> {
            Departamento departamento = departamentoDAO.findById(empleadodep.getCodDepartamento()).orElseThrow(() ->
                    new EntityNotFoundException());

            EmpleadoDep empleadoDep = new EmpleadoDep
                    (empleadodep.getId(),empleadodep.getCargo(),
                     departamento,empleadoGuardado);
            empleadoDepDAO.save(empleadoDep);
        });

        return empleadoMapper.convertToEmpleadoOutputDto(empleadoDAO.save(empleado));
    }


    @Override
    public EmpleadoOutputDto findByEmpleado(Long idEmpleado) {
        Optional<Empleado> opEmployable = empleadoDAO.findById(idEmpleado);
        EmpleadoOutputDto employableOutDto = new EmpleadoOutputDto();
        if (opEmployable.isPresent()) {
            BeanUtils.copyProperties(opEmployable.get(), employableOutDto);
        }
        return employableOutDto;
    }

    @Override
    public EmpleadoMinOutputDto findMinByEmpleado(Long idEmployable) {
        Optional<Empleado> opEmployable = empleadoDAO.findById(idEmployable);
        EmpleadoMinOutputDto employableMinOutDto = new EmpleadoMinOutputDto();
        if (opEmployable.isPresent()) {
            BeanUtils.copyProperties(opEmployable.get(), employableMinOutDto);
        }
        return employableMinOutDto;
    }

}
