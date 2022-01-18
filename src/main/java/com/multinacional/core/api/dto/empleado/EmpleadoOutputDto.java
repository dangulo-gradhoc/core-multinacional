package com.multinacional.core.api.dto.empleado;

import com.multinacional.core.api.dto.departamento.DepartamentoOutputMinDto;
import lombok.Data;

import java.util.Set;

@Data
public class EmpleadoOutputDto {

    private Long id;
    private String dni;
    private String nombre;
    private String telefono;
}
