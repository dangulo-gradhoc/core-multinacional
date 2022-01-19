package com.multinacional.core.ws.controller;

import java.util.List;
import java.util.Optional;

import com.multinacional.core.api.dto.departamento.DepartamentoMinOutputDto;
import com.multinacional.core.api.dto.generic.ListaGenericDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.multinacional.core.api.service.IDepartamentoService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/departamentos")
public class DepartamentoController {

    @Autowired
    private IDepartamentoService departamentoService;

    @GetMapping("/all")
    public ResponseEntity<List<DepartamentoMinOutputDto>> findAll() {

        return ResponseEntity.ok(departamentoService.findAll());
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<DepartamentoMinOutputDto> findByTipoId(@PathVariable Long id) {
        if (id == null) {
            return ResponseEntity.badRequest().build();
        }
        log.debug("findByDepartamentoId {}", id);
        return ResponseEntity.ok(departamentoService.findByDepartamento(id));

    }

}