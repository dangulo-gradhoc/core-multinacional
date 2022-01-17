package com.multinacional.core.ws.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.multinacional.core.api.dto.tipo.TipoOutputDto;
import com.multinacional.core.api.service.ITipoService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/tipos")
public class TipoController {

    @Autowired
    private ITipoService tipoService;

    @GetMapping("/all")
    public ResponseEntity<List<TipoOutputDto>> findAll() {

        return ResponseEntity.ok(tipoService.findAll());
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<TipoOutputDto> findByTipoId(@PathVariable Long id) {
        if (id == null) {
            return ResponseEntity.badRequest().build();
        }
        log.debug("findByTipoId {}", id);
        return ResponseEntity.ok(tipoService.findByTipo(id));

    }
}