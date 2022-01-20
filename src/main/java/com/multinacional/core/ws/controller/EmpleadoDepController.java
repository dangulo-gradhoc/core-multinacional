package com.multinacional.core.ws.controller;

import com.multinacional.core.api.dto.empleadodep.EmpleadoDepInputDto;
import com.multinacional.core.api.dto.empleadodep.EmpleadoDepMinOutputDto;
import com.multinacional.core.api.dto.empleadodep.EmpleadoDepOutputDto;
import com.multinacional.core.api.service.IEmpleadoDepService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
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

    @PostMapping("/create")
    public ResponseEntity<EmpleadoDepOutputDto> create(@Valid @RequestBody EmpleadoDepInputDto inputDto) {
        log.debug("EmpleadoDep create");
        try {
            return ResponseEntity.ok(empleadoDepService.create(inputDto));
        } catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().build();
        }catch (EntityNotFoundException en){
            return ResponseEntity.notFound().build();
        }
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
