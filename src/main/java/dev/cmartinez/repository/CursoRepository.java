package dev.cmartinez.repository;

import dev.cmartinez.entity.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {
    
    // Buscar cursos por nombre (contiene)
    List<Curso> findByNombreContainingIgnoreCase(String nombre);
    
    // Buscar cursos por profesor
    List<Curso> findByProfesorContainingIgnoreCase(String profesor);
    
    // Buscar cursos activos
    List<Curso> findByActivoTrue();
    
    // Buscar curso por nombre exacto
    Optional<Curso> findByNombre(String nombre);
    
    // Buscar cursos por categoría (nuevo método)
    List<Curso> findByCategoriaId(Long categoriaId);
}
