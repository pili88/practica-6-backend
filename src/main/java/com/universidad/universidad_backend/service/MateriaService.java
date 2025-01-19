package com.universidad.universidad_backend.service;

import com.universidad.universidad_backend.model.Materia;
import com.universidad.universidad_backend.repository.MateriaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class MateriaService {
    private final MateriaRepository materiaRepository;

    public Materia save(Materia materia) {
        log.info("Guardando nueva materia: {}", materia.getNombre());
        return materiaRepository.save(materia);
    }

    public List<Materia> findByAlumnoId(Long alumnoId) {
        log.info("Buscando materias para el alumno ID: {}", alumnoId);
        return materiaRepository.findByAlumnoId(alumnoId);
    }
}