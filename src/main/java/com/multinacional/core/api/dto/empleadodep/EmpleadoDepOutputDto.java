package com.multinacional.core.api.dto.empleadodep;

import com.multinacional.core.api.dto.departamento.DepartamentoMinOutputDto;
import com.multinacional.core.api.dto.empleado.EmpleadoMinOutputDto;
import lombok.Data;

@Data
public class     EmpleadoDepOutputDto {

    private Long id;

    private String cargo;

    private Long codEmpleado;
    private Long codDepartamento;

    private EmpleadoMinOutputDto empleado;

    private DepartamentoMinOutputDto departamento;


}
