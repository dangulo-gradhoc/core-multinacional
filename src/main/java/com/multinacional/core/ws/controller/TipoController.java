package com.multinacional.core.ws.controller;

import java.util.List;

import com.multinacional.core.api.dto.tipo.TipoMinInputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.multinacional.core.api.dto.tipo.TipoMinOutputDto;
import com.multinacional.core.api.service.ITipoService;

import lombok.extern.slf4j.Slf4j;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/tipos")
public class TipoController {

    @Autowired
    private ITipoService tipoService;

    @GetMapping("/all")
    public ResponseEntity<List<TipoMinOutputDto>> findAll() {

        return ResponseEntity.ok(tipoService.findAll());
    }

    @PostMapping("/create")
    public ResponseEntity<TipoMinOutputDto> create(@Valid @RequestBody TipoMinInputDto inputDto){

        try{

            return ResponseEntity.ok(tipoService.create(inputDto));

        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().build();
        }

    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<TipoMinOutputDto> findByTipoId(@PathVariable Long id) {
        if (id == null) {
            return ResponseEntity.badRequest().build();
        }
        log.debug("findByTipoId {}", id);
        return ResponseEntity.ok(tipoService.findByTipo(id));

    }
}