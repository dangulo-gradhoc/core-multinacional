package com.multinacional.core.api.dto.empleadodep;

import lombok.Data;

@Data
public class EmpleadoDepInputDto {

    private Long id;
    private String cargo;
    private Long codEmpleado;
    private Long codDepartamento;


}
