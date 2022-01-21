package com.multinacional.core.model.repositoryJdbc;

import com.multinacional.core.api.dto.empresa.EmpresaMinOutputDto;
import com.multinacional.core.model.entity.Empresa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Set;

public interface IJdbcEmpresaRepository {

    Page<EmpresaMinOutputDto> findAllEmpresasMinByTipo(String nombreTipo, Pageable paging);

    void deleteAllDepartamento(final Long codEmpresa);

    void deleteDepartamentos(Long id, Set<Long> codsDepartamentos);

}