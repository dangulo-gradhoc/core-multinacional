package com.multinacional.core.api.dto.empleado;

import com.multinacional.core.api.dto.empleadodep.EmpleadoDepOutputDto;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class EmpleadoOutputDto {

    private Long id;
    private String dni;
    private String nombre;
    private String telefono;
    private List<EmpleadoDepOutputDto> empleadodep;

}