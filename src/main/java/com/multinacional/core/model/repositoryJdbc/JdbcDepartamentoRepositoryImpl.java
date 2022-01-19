package com.multinacional.core.model.repositoryJdbc;

import com.multinacional.core.api.dto.departamento.DepartamentoMinOutputDto;
import com.multinacional.core.api.dto.empresa.EmpresaMinOutputDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class JdbcDepartamentoRepositoryImpl implements IJdbcDepartamentoRepository{

    private final JdbcTemplate jdbcTemplate;

    @Override
    public Page<DepartamentoMinOutputDto> findAllDepartamentoByEmpresa(Long idEmpresa, Pageable paging) {
        StringBuilder query = new StringBuilder();
        query.append(" SELECT D.id , D.nombre FROM multinacional.departamento D ")
                .append( " INNER JOIN multinacional.empresadep ED ON D.id=ED.codDepartamento ")
                .append(" INNER JOIN multinacional.empresa E ON ED.codEmpresa=E.id ")
                .append(" WHERE E.id = ? ");

        List<DepartamentoMinOutputDto> departamentosList = jdbcTemplate.query(query.toString(), new BeanPropertyRowMapper<>(DepartamentoMinOutputDto.class), idEmpresa);
        return new PageImpl<>(departamentosList, paging, countDepartamentoByEmpresa(idEmpresa));
    }
    private Long countDepartamentoByEmpresa(Long idEmpresa){

        StringBuilder query = new StringBuilder();
        query.append(" SELECT COUNT(*) FROM multinacional.departamento D ")
                .append( " INNER JOIN multinacional.empresadep ED ON D.id=ED.codDepartamento ")
                .append(" INNER JOIN multinacional.empresa E ON ED.codEmpresa=E.id ")
                .append(" WHERE E.id = ? ");


        return jdbcTemplate.queryForObject(query.toString(), Long.class, idEmpresa);
    }
}