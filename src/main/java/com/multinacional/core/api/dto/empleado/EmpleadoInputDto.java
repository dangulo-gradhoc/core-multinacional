package com.multinacional.core.api.dto.empleado;

import com.multinacional.core.api.dto.empleadodep.EmpleadoDepInputDto;
import lombok.Data;

import java.util.Set;

@Data
public class EmpleadoInputDto {

    private Long id;
    private String dni;
    private String nombre;
    private String telefono;
    private Set<EmpleadoDepInputDto> empleadodep;
}
