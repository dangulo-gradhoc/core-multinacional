package com.multinacional.core.api.service;

import com.multinacional.core.api.dto.empresa.EmpresaMinOutputDto;
import com.multinacional.core.api.dto.empresa.EmpresaOutputDto;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IEmpresaService {
    List<EmpresaOutputDto> findAll();

    EmpresaOutputDto findByEmpresaId(Long idEmpresa);

    EmpresaMinOutputDto findMinByEmpresaID(Long idEmpresa);
}
