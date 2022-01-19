package com.multinacional.core.ws.controller;

import java.util.List;
import java.util.Optional;

import com.multinacional.core.api.dto.empresa.EmpresaInputDto;
import com.multinacional.core.api.dto.generic.ListaGenericDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.multinacional.core.api.dto.empresa.EmpresaMinOutputDto;
import com.multinacional.core.api.dto.empresa.EmpresaOutputDto;
import com.multinacional.core.api.service.IEmpresaService;

import lombok.extern.slf4j.Slf4j;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

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

    @PostMapping("/crear")
    public ResponseEntity<EmpresaOutputDto> create(@Valid @RequestBody EmpresaInputDto inputDto){
        log.debug("Empresa create");
        try{
            return ResponseEntity.ok(empresaService.create(inputDto));
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().build();
        }
        catch (EntityNotFoundException en){
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/listar/{id}")
    public ResponseEntity<EmpresaOutputDto> findByEmpresa(@PathVariable Long id) {
        if (id == null) {
            return ResponseEntity.badRequest().build();
        }
        log.debug("findByEmpresaId {}", id);
        return ResponseEntity.ok(empresaService.findByEmpresa(id));

    }

    @GetMapping("/listarMin/{id}")
    public ResponseEntity<EmpresaMinOutputDto> findMinByEmpresa(@PathVariable Long id) {
        if (id == null) {
            return ResponseEntity.badRequest().build();
        }
        log.debug("findByEmpresaId {}", id);
        return ResponseEntity.ok(empresaService.findMinByEmpresa(id));

    }

    @GetMapping("/tipos/listarMin/{nombreTipo}")
    public ResponseEntity<ListaGenericDto<EmpresaMinOutputDto>> findAllEmpresasMinByInstalacion(@PathVariable String nombreTipo,
                                                                                                @RequestParam Optional<Integer> pageNo,
                                                                                                @RequestParam Optional<Integer> pageSize){

        return ResponseEntity.ok(empresaService.findAllEmpresasMinByTipo(nombreTipo, pageNo, pageSize));
    }
}
