package com.multinacional.core.ws.controller;

import com.multinacional.core.api.dto.empleado.EmpleadoInputDto;
import com.multinacional.core.api.dto.empleado.EmpleadoMinOutputDto;
import com.multinacional.core.api.dto.empleado.EmpleadoOutputDto;
import com.multinacional.core.api.service.IEmpleadoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/empleados")
@RequiredArgsConstructor
public class EmpleadoController {

    @Autowired
    private IEmpleadoService empleadoService;

    @GetMapping
    public ResponseEntity<List<EmpleadoOutputDto>> findAll() {

        return ResponseEntity.ok(empleadoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmpleadoOutputDto> findByEmpleado(@PathVariable Long id) {
        if (id == null) {
            return ResponseEntity.badRequest().build();
        }
        log.debug("findByEmpleadoId {}", id);
        return ResponseEntity.ok(empleadoService.findByEmpleado(id));

    }

    @GetMapping("/min/{id}")
    public ResponseEntity<EmpleadoMinOutputDto> findMinByEmpleado(@PathVariable Long id) {
        if (id == null) {
            return ResponseEntity.badRequest().build();
        }
        log.debug("findByEmpleadoId {}", id);
        return ResponseEntity.ok(empleadoService.findMinByEmpleado(id));

    }

    @PostMapping
    public  ResponseEntity<EmpleadoOutputDto> create(@Valid @RequestBody EmpleadoInputDto inputDto){
        log.debug("Empleado create");
        try{
        return ResponseEntity.ok(empleadoService.create(inputDto));

        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().build();
        }catch (EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }
    @PatchMapping
    public ResponseEntity<EmpleadoOutputDto>  update(@Valid @RequestBody EmpleadoInputDto inputDto) {
        log.debug("Empleado update {}", inputDto.getId());
        try {
            return ResponseEntity.ok(empleadoService.update(inputDto));

        }catch(IllegalArgumentException e){
            return ResponseEntity.badRequest().build();
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id){
        try{
            return ResponseEntity.ok(empleadoService.delete(id));
        }catch(EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }
}
