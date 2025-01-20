package com.universidad.universidad_backend.controller;

import com.universidad.universidad_backend.model.Alumno;
import com.universidad.universidad_backend.service.AlumnoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alumnos")
@RequiredArgsConstructor
public class AlumnoController {
    private final AlumnoService alumnoService;

    @GetMapping
    public ResponseEntity<List<Alumno>> findAll() {
        return ResponseEntity.ok(alumnoService.findAll());
    }

    @PostMapping
    public ResponseEntity<Alumno> save(@RequestBody Alumno alumno) {
        return ResponseEntity.ok(alumnoService.save(alumno));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Alumno> findById(@PathVariable Long id) {
        return alumnoService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
