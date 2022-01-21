package com.multinacional.core.model.repositoryJdbc;

import com.multinacional.core.api.dto.empresa.EmpresaMinOutputDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Repository
@RequiredArgsConstructor
public class JdbcEmpresaRepositoryImpl implements IJdbcEmpresaRepository{

    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate nameJdbc;

    @Override
    public Page<EmpresaMinOutputDto> findAllEmpresasMinByTipo(String nombreTipo, Pageable paging) {
        StringBuilder query = new StringBuilder();
        query.append("SELECT E.id, E.nombre FROM multinacional.empresa E INNER JOIN multinacional.tipo T ON E.codTipo=T.id WHERE T.nombre =  ?");

        List<EmpresaMinOutputDto> empresasList = jdbcTemplate.query(query.toString(), new BeanPropertyRowMapper<>(EmpresaMinOutputDto.class), nombreTipo);
        return new PageImpl<>(empresasList, paging, countEmpresaMinByTipo(nombreTipo));
    }
    @Override
    public void deleteAllDepartamento(final Long codEmpresa) {
        final StringBuilder qry = new StringBuilder();
        qry.append(" DELETE FROM multinacional.empresadep ");
        qry.append(" WHERE codEmpresa = :codEmpresa  ");

        final Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("codEmpresa", codEmpresa);
        nameJdbc.update(qry.toString(), paramMap);
    }

    @Override
    public void deleteDepartamentos(Long codEmpresa, Set<Long> codsDepartamentos) {
        final StringBuilder qry = new StringBuilder();
        qry.append(" DELETE FROM multinacional.empresadep ");
        qry.append(" WHERE codEmpresa = :codEmpresa ");
        qry.append(" AND codDepartamento IN (:codsDepartamentos) ");

        final Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("codEmpresa", codEmpresa);
        paramMap.put("codsDepartamentos", codsDepartamentos);
        nameJdbc.update(qry.toString(), paramMap);
    }

    private Long countEmpresaMinByTipo(String nombreTipo){

        StringBuilder query = new StringBuilder();
        query.append("SELECT COUNT(*) FROM multinacional.empresa E INNER JOIN multinacional.tipo T ON E.codTipo=T.id WHERE T.nombre = ?");


        return jdbcTemplate.queryForObject(query.toString(), Long.class, nombreTipo);
    }

}
