package com.multinacional.core.ws.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.multinacional.core.api.dto.departamento.DepartamentoOutputDto;
import com.multinacional.core.api.service.IDepartamentoService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/departamentos")
public class DepartamentoController {

    @Autowired
    private IDepartamentoService departamentoService;

    @GetMapping("/all")
    public ResponseEntity<List<DepartamentoOutputDto>> findAll() {

        return ResponseEntity.ok(departamentoService.findAll());
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<DepartamentoOutputDto> findByTipoId(@PathVariable Long id) {
        if (id == null) {
            return ResponseEntity.badRequest().build();
        }
        log.debug("findByDepartamentoId {}", id);
        return ResponseEntity.ok(departamentoService.findByDepartamento(id));

    }
}