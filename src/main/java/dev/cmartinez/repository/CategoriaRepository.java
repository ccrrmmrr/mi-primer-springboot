package dev.cmartinez.repository;

import dev.cmartinez.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    
    // Buscar categoría por nombre exacto
    Optional<Categoria> findByNombre(String nombre);
    
    // Buscar categorías por nombre (contiene)
    List<Categoria> findByNombreContainingIgnoreCase(String nombre);
    
    // Buscar categorías activas
    List<Categoria> findByActivoTrue();
    
    // Verificar si existe categoría con nombre (para validaciones)
    boolean existsByNombre(String nombre);
    
    // Verificar si existe categoría con nombre excluyendo un ID (para updates)
    boolean existsByNombreAndIdNot(String nombre, Long id);
    
    // Contar cursos asociados a una categoría
    @Query("SELECT COUNT(c) FROM Curso c WHERE c.categoria.id = :categoriaId")
    Long countCursosByCategoriaId(@Param("categoriaId") Long categoriaId);
}
