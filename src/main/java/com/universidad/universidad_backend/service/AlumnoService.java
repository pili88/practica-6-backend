package com.universidad.universidad_backend.service;

import com.universidad.universidad_backend.model.Alumno;
import com.universidad.universidad_backend.repository.AlumnoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class AlumnoService {
    private final AlumnoRepository alumnoRepository;

    public List<Alumno> findAll() {
        log.info("Buscando todos los alumnos");
        return alumnoRepository.findAll();
    }

    public Alumno save(Alumno alumno) {
        log.info("Guardando nuevo alumno: {}", alumno.getNombre());
        return alumnoRepository.save(alumno);
    }

    public Optional<Alumno> findById(Long id) {
        log.info("Buscando alumno por ID: {}", id);
        return alumnoRepository.findById(id);
    }
}