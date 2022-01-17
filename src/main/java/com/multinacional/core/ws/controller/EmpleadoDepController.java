package com.multinacional.core.ws.controller;

import com.multinacional.core.api.dto.empleadodep.EmpleadoDepMinOutputDto;
import com.multinacional.core.api.dto.empleadodep.EmpleadoDepOutputDto;
import com.multinacional.core.api.service.IEmpleadoDepService;
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
@RequestMapping("/empleadosdep")
public class EmpleadoDepController {

    @Autowired
    private IEmpleadoDepService empleadoDepService;

    @GetMapping("/all")
    public ResponseEntity<List<EmpleadoDepOutputDto>> findAll() {

        return ResponseEntity.ok(empleadoDepService.findAll());
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<EmpleadoDepOutputDto> findByEmpleadoDep(@PathVariable Long id) {
        if (id == null) {
            return ResponseEntity.badRequest().build();
        }
        log.debug("findByEmpleadoDep {}", id);
        return ResponseEntity.ok(empleadoDepService.findByEmpleadoDep(id));

    }

    @GetMapping("/listarMin/{id}")
    public ResponseEntity<EmpleadoDepMinOutputDto> findMinByEmpleadoDep(@PathVariable Long id) {
        if (id == null) {
            return ResponseEntity.badRequest().build();
        }
        log.debug("findByEmpleadoDep {}", id);
        return ResponseEntity.ok(empleadoDepService.findMinByEmpleadoDep(id));

    }

}
