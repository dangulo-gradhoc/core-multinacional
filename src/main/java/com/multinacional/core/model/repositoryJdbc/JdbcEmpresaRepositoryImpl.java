package com.multinacional.core.model.repositoryJdbc;

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
public class JdbcEmpresaRepositoryImpl implements IJdbcEmpresaRepository{

    private final JdbcTemplate jdbcTemplate;

    @Override
    public Page<EmpresaMinOutputDto> findAllEmpresasMinByTipo(String nombreTipo, Pageable paging) {
        StringBuilder query = new StringBuilder();
        query.append("SELECT E.id, E.nombre FROM multinacional.empresa E INNER JOIN multinacional.tipo T ON E.codTipo=T.id WHERE T.nombre =  ?");

        List<EmpresaMinOutputDto> empresasList = jdbcTemplate.query(query.toString(), new BeanPropertyRowMapper<>(EmpresaMinOutputDto.class), nombreTipo);
        return new PageImpl<>(empresasList, paging, countEmpresaMinByInstalacion(nombreTipo));
    }
    private Long countEmpresaMinByInstalacion(String nombreTipo){

        StringBuilder query = new StringBuilder();
        query.append("SELECT COUNT(*) FROM multinacional.empresa E INNER JOIN multinacional.tipo T ON E.codTipo=T.id WHERE T.nombre = ?");


        return jdbcTemplate.queryForObject(query.toString(), Long.class, nombreTipo);
    }

}
