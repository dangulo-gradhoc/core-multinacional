package com.multinacional.core.api.dto.empleadodep;

import com.multinacional.core.api.dto.departamento.DepartamentoOutputMinDto;
import com.multinacional.core.api.dto.empleado.EmpleadoMinOutputDto;
import lombok.Data;

@Data
public class EmpleadoDepOutputDto {

    private Long id;

    private EmpleadoMinOutputDto empleado;

    private DepartamentoOutputMinDto departamento;

    private String cargo;

}
