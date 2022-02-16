package com.multinacional.core.ws.controller;

import java.util.List;

import com.multinacional.core.api.dto.departamento.DepartamentoMinInputDto;
import com.multinacional.core.api.dto.departamento.DepartamentoMinOutputDto;
import com.multinacional.core.api.dto.tipo.TipoMinInputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.multinacional.core.api.dto.tipo.TipoMinOutputDto;
import com.multinacional.core.api.service.ITipoService;

import lombok.extern.slf4j.Slf4j;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

@Slf4j
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/tipos")
public class TipoController {

    @Autowired
    private ITipoService tipoService;

    @GetMapping
    public ResponseEntity<List<TipoMinOutputDto>> findAll() {

        return ResponseEntity.ok(tipoService.findAll());
    }
    @PatchMapping
    public ResponseEntity<TipoMinOutputDto>  update(@Valid @RequestBody TipoMinInputDto inputDto) {
        log.debug("Tipo update {}", inputDto.getId());
        try {
            return ResponseEntity.ok(tipoService.update(inputDto));

        }catch(IllegalArgumentException e){
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping
    public ResponseEntity<TipoMinOutputDto> create(@Valid @RequestBody TipoMinInputDto inputDto){

        try{

            return ResponseEntity.ok(tipoService.create(inputDto));

        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().build();
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoMinOutputDto> findByTipoId(@PathVariable Long id) {
        if (id == null) {
            return ResponseEntity.badRequest().build();
        }
        log.debug("findByTipoId {}", id);
        return ResponseEntity.ok(tipoService.findByTipo(id));

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id){
        try {
            return ResponseEntity.ok(tipoService.delete(id));
        }catch (EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }
}