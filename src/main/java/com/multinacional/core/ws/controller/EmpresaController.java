package com.multinacional.core.ws.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.multinacional.core.api.dto.empresa.EmpresaMinOutputDto;
import com.multinacional.core.api.dto.empresa.EmpresaOutputDto;
import com.multinacional.core.api.service.IEmpresaService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/empresas")
public class EmpresaController {

    @Autowired
    private IEmpresaService empresaService;

    @GetMapping("/all")
    public ResponseEntity<List<EmpresaOutputDto>> findAll() {

        return ResponseEntity.ok(empresaService.findAll());
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<EmpresaOutputDto> findByEmpresaId(@PathVariable Long id) {
        if (id == null) {
            return ResponseEntity.badRequest().build();
        }
        log.debug("findByEmpresaId {}", id);
        return ResponseEntity.ok(empresaService.findByEmpresaId(id));

    }

    @GetMapping("/listarMin/{id}")
    public ResponseEntity<EmpresaMinOutputDto> findMinByEmpresaId(@PathVariable Long id) {
        if (id == null) {
            return ResponseEntity.badRequest().build();
        }
        log.debug("findByEmpresaId {}", id);
        return ResponseEntity.ok(empresaService.findMinByEmpresaID(id));

    }
}
