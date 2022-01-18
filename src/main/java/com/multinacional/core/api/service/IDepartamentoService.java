package com.multinacional.core.api.service;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.multinacional.core.api.dto.departamento.DepartamentoOutputMinDto;

@Repository
public interface IDepartamentoService {

    List<DepartamentoOutputMinDto> findAll();

    DepartamentoOutputMinDto findByDepartamento(Long idDepartamento);
}