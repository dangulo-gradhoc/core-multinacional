package com.multinacional.core.api.service;

import com.multinacional.core.api.dto.empresa.EmpresaMinOutputDto;
import com.multinacional.core.api.dto.empresa.EmpresaOutputDto;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface IEmpresaService {
    List<EmpresaOutputDto> findAll();

    EmpresaOutputDto findByEmpresa(Long idEmpresa);

    EmpresaMinOutputDto findMinByEmpresa(Long idEmpresa);
}
