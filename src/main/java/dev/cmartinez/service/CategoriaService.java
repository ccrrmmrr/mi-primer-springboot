package dev.cmartinez.service;

import dev.cmartinez.dto.CategoriaDTO;
import dev.cmartinez.entity.Categoria;
import dev.cmartinez.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    // Convertir Entity a DTO
    private CategoriaDTO convertToDTO(Categoria categoria) {
        CategoriaDTO dto = new CategoriaDTO();
        dto.setId(categoria.getId());
        dto.setNombre(categoria.getNombre());
        dto.setDescripcion(categoria.getDescripcion());
        dto.setFechaCreacion(categoria.getFechaCreacion());
        dto.setActivo(categoria.getActivo());
        return dto;
    }

    // Convertir DTO a Entity
    private Categoria convertToEntity(CategoriaDTO categoriaDTO) {
        Categoria categoria = new Categoria();
        categoria.setId(categoriaDTO.getId());
        categoria.setNombre(categoriaDTO.getNombre());
        categoria.setDescripcion(categoriaDTO.getDescripcion());
        categoria.setActivo(categoriaDTO.getActivo());
        return categoria;
    }

    // Obtener todas las categorías
    public List<CategoriaDTO> findAll() {
        return categoriaRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Obtener categoría por ID
    public Optional<CategoriaDTO> findById(Long id) {
        return categoriaRepository.findById(id)
                .map(this::convertToDTO);
    }

    // Guardar categoría (crear o actualizar)
    public CategoriaDTO save(CategoriaDTO categoriaDTO) {
        Categoria categoria = convertToEntity(categoriaDTO);
        Categoria savedCategoria = categoriaRepository.save(categoria);
        return convertToDTO(savedCategoria);
    }

    // Eliminar categoría (con validación)
    public boolean deleteById(Long id) {
        if (categoriaRepository.existsById(id)) {
            // Verificar si tiene cursos asociados
            Long cursosCount = categoriaRepository.countCursosByCategoriaId(id);
            if (cursosCount > 0) {
                return false; // No se puede eliminar, tiene cursos
            }
            categoriaRepository.deleteById(id);
            return true; // Eliminación exitosa
        }
        return false; // Categoría no existe
    }

    // Buscar categorías por nombre
    public List<CategoriaDTO> findByNombreContaining(String nombre) {
        return categoriaRepository.findByNombreContainingIgnoreCase(nombre)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Obtener categorías activas
    public List<CategoriaDTO> findActiveCategorias() {
        return categoriaRepository.findByActivoTrue()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Verificar si existe categoría con nombre
    public boolean existsByNombre(String nombre) {
        return categoriaRepository.existsByNombre(nombre);
    }

    // Verificar si existe categoría con nombre excluyendo ID
    public boolean existsByNombreAndIdNot(String nombre, Long id) {
        return categoriaRepository.existsByNombreAndIdNot(nombre, id);
    }

    // Obtener entidad Categoria por ID (para relaciones)
    public Optional<Categoria> findEntityById(Long id) {
        return categoriaRepository.findById(id);
    }
}
