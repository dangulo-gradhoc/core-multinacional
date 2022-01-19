package com.multinacional.core.model.repositoryJdbc;

import com.multinacional.core.api.dto.departamento.DepartamentoMinOutputDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IJdbcDepartamentoRepository {

    Page<DepartamentoMinOutputDto> findAllDepartamentoByEmpresa(Long idEmpresa, Pageable paging);

}
