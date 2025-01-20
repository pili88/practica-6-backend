package com.universidad.universidad_backend.controller;

import com.universidad.universidad_backend.model.Materia;
import com.universidad.universidad_backend.service.MateriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/materias")
@RequiredArgsConstructor
public class MateriaController {
    private final MateriaService materiaService;

    @PostMapping
    public ResponseEntity<Materia> save(@RequestBody Materia materia) {
        return ResponseEntity.ok(materiaService.save(materia));
    }

    @GetMapping("/alumno/{alumnoId}")
    public ResponseEntity<List<Materia>> findByAlumnoId(@PathVariable Long alumnoId) {
        return ResponseEntity.ok(materiaService.findByAlumnoId(alumnoId));
    }
}

