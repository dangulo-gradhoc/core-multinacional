package com.multinacional.core.api.dto.empresa;

import lombok.Data;

import java.util.Set;

@Data
public class EmpresaInputDto {

    private Long id;

    private String nombre;

    private String direccion;

    private Long codTipo;

    private Set<Long> codsDepartamentos;

}
