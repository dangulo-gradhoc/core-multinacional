package com.multinacional.core.ws.controller;

import com.multinacional.core.api.dto.empleado.EmpleadoMinOutputDto;
import com.multinacional.core.api.dto.empleado.EmpleadoOutputDto;
import com.multinacional.core.api.service.IEmpleadoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/empleados")
public class EmpleadoController {

    @Autowired
    private IEmpleadoService empleadoService;

    @GetMapping("/all")
    public ResponseEntity<List<EmpleadoOutputDto>> findAll() {

        return ResponseEntity.ok(empleadoService.findAll());
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<EmpleadoOutputDto> findByEmpleado(@PathVariable Long id) {
        if (id == null) {
            return ResponseEntity.badRequest().build();
        }
        log.debug("findByEmpleadoId {}", id);
        return ResponseEntity.ok(empleadoService.findByEmpleado(id));

    }

    @GetMapping("/listarMin/{id}")
    public ResponseEntity<EmpleadoMinOutputDto> findMinByEmpleado(@PathVariable Long id) {
        if (id == null) {
            return ResponseEntity.badRequest().build();
        }
        log.debug("findByEmpleadoId {}", id);
        return ResponseEntity.ok(empleadoService.findMinByEmpleado(id));

    }

}
