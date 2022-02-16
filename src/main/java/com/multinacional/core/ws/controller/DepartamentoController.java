package com.multinacional.core.ws.controller;

import com.multinacional.core.api.dto.departamento.DepartamentoMinInputDto;
import com.multinacional.core.api.dto.departamento.DepartamentoMinOutputDto;
import com.multinacional.core.api.dto.empleadodep.EmpleadoDepOutputDto;
import com.multinacional.core.api.dto.generic.ListaGenericDto;
import com.multinacional.core.api.service.IDepartamentoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/departamentos")
public class DepartamentoController {

    @Autowired
    private IDepartamentoService departamentoService;

    @GetMapping
    public ResponseEntity<List<DepartamentoMinOutputDto>> findAll() {

        return ResponseEntity.ok(departamentoService.findAll());
    }

    @PostMapping
    public ResponseEntity<DepartamentoMinOutputDto> create(@Valid @RequestBody DepartamentoMinInputDto departamentoMinInputDto) {
        log.debug("Departamento create");
        try {
            return ResponseEntity.ok(departamentoService.create(departamentoMinInputDto));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PatchMapping
    public ResponseEntity<DepartamentoMinOutputDto> update(@Valid @RequestBody DepartamentoMinInputDto inputDto) {
        log.debug("Departamento update {}", inputDto.getId());
        try {
            return ResponseEntity.ok(departamentoService.update(inputDto));

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartamentoMinOutputDto> findByTipoId(@PathVariable Long id) {
        if (id == null) {
            return ResponseEntity.badRequest().build();
        }
        log.debug("findByDepartamentoId {}", id);
        return ResponseEntity.ok(departamentoService.findByDepartamento(id));

    }

    @GetMapping("/empresas/{idEmpresa}")
    public ResponseEntity<ListaGenericDto<DepartamentoMinOutputDto>> findAllDepartamentosMinByEmpresas(@PathVariable Long idEmpresa,
                                                                                                       @RequestParam Optional<Integer> pageNo,
                                                                                                       @RequestParam Optional<Integer> pageSize) {
        try {
            return ResponseEntity.ok(departamentoService.findAllDepartamentosByEmpresas(idEmpresa, pageNo, pageSize));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/empleados/{idEmpleado}")
    public ResponseEntity<ListaGenericDto<EmpleadoDepOutputDto>> findAllDepartamentosByEmpleados(@PathVariable Long idEmpleado,
                                                                                                 @RequestParam Optional<Integer> pageNo,
                                                                                                 @RequestParam Optional<Integer> pageSize) {

        return ResponseEntity.ok(departamentoService.findAllDepartamentosByEmpleados(idEmpleado, pageNo, pageSize));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(departamentoService.delete(id));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}