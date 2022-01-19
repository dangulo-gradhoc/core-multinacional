package com.multinacional.core.ws.controller;

import com.multinacional.core.api.dto.departamento.DepartamentoInputDto;
import com.multinacional.core.api.dto.departamento.DepartamentoMinOutputDto;
import com.multinacional.core.api.dto.generic.ListaGenericDto;
import com.multinacional.core.api.dto.generic.Mensaje;
import com.multinacional.core.api.service.IDepartamentoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

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
    @PostMapping("/crear")
    public ResponseEntity<DepartamentoMinOutputDto>  create(@Valid @RequestBody DepartamentoInputDto departamentoInputDto) {
        log.debug("Departamento create");
        try {
            return ResponseEntity.ok(departamentoService.create(departamentoInputDto));
        } catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<DepartamentoMinOutputDto> findByTipoId(@PathVariable Long id) {
        if (id == null) {
            return ResponseEntity.badRequest().build();
        }
        log.debug("findByDepartamentoId {}", id);
        return ResponseEntity.ok(departamentoService.findByDepartamento(id));

    }

    @GetMapping("/empresas/listar/{idEmpresa}")
    public ResponseEntity<ListaGenericDto<DepartamentoMinOutputDto>> findAllDepartamentosMinByEmpresas(@PathVariable Long idEmpresa,
                                                                                                @RequestParam Optional<Integer> pageNo,
                                                                                                @RequestParam Optional<Integer> pageSize){

        return ResponseEntity.ok(departamentoService.findAllDepartamentosByEmpresas(idEmpresa, pageNo, pageSize));
    }

}