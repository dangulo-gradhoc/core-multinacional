package com.multinacional.core.api.service;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.multinacional.core.api.dto.departamento.DepartamentoOutputDto;

@Repository
public interface IDepartamentoService {

    List<DepartamentoOutputDto> findAll();

    DepartamentoOutputDto findByDepartamento(Long idDepartamento);
}