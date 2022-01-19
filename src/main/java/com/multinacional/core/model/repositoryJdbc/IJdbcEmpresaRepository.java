package com.multinacional.core.model.repositoryJdbc;

import com.multinacional.core.api.dto.empresa.EmpresaMinOutputDto;
import com.multinacional.core.model.entity.Empresa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IJdbcEmpresaRepository {

    Page<EmpresaMinOutputDto> findAllEmpresasMinByTipo(String nombreTipo, Pageable paging);

}
