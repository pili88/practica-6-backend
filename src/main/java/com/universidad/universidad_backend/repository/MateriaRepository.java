package com.universidad.universidad_backend.repository;

import com.universidad.universidad_backend.model.Materia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MateriaRepository extends JpaRepository<Materia, Long> {
    List<Materia> findByAlumnoId(Long alumnoId);
}
