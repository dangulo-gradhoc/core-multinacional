package com.multinacional.core.api.dto.empleadodep;

import com.multinacional.core.api.dto.departamento.DepartamentoMinOutputDto;
import com.multinacional.core.api.dto.empleado.EmpleadoMinOutputDto;
import lombok.Data;

@Data
public class EmpleadoDepOutputDto {

    private Long id;

    private EmpleadoMinOutputDto empleado;

    private DepartamentoMinOutputDto departamento;

    private String cargo;

}
