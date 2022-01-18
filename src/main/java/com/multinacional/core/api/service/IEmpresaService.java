package com.multinacional.core.api.service;

import com.multinacional.core.api.dto.empresa.EmpresaMinOutputDto;
import com.multinacional.core.api.dto.empresa.EmpresaOutputDto;
import com.multinacional.core.api.dto.generic.ListaGenericDto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface IEmpresaService {
    List<EmpresaOutputDto> findAll();

    EmpresaOutputDto findByEmpresa(Long idEmpresa);

    EmpresaMinOutputDto findMinByEmpresa(Long idEmpresa);

    ListaGenericDto<EmpresaMinOutputDto> findAllEmpresasMinByTipo(String nombreTipo, Optional<Integer> pageNo, Optional<Integer> pageSize);
}
