package com.multinacional.core.api.dto.empleado;

import lombok.Data;

@Data
public class EmpleadoInputDto {

    private Long id;
    private String dni;
    private String nombre;
    private String telefono;

}
