package com.multinacional.core.api.service;

import com.multinacional.core.api.dto.empresa.EmpresaInputDto;
import com.multinacional.core.api.dto.empresa.EmpresaMinOutputDto;
import com.multinacional.core.api.dto.empresa.EmpresaOutputDto;
import com.multinacional.core.api.dto.generic.ListaGenericDto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface IEmpresaService {
    List<EmpresaOutputDto> findAll();

    EmpresaOutputDto create(EmpresaInputDto inputDto);

    EmpresaOutputDto update(EmpresaInputDto inputDto);

    Boolean delete(Long id);

    EmpresaOutputDto findByEmpresa(Long idEmpresa);

    EmpresaMinOutputDto findMinByEmpresa(Long idEmpresa);

    ListaGenericDto<EmpresaMinOutputDto> findAllEmpresasMinByTipo(String nombreTipo, Optional<Integer> pageNo, Optional<Integer> pageSize);
}
