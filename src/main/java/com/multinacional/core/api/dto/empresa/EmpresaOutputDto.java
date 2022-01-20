package com.multinacional.core.api.dto.empresa;

import com.multinacional.core.api.dto.tipo.TipoMinOutputDto;
import lombok.Data;

@Data
public class EmpresaOutputDto {

    private Long id;

    private String nombre;

    private String direccion;

    private TipoMinOutputDto tipo;
}
