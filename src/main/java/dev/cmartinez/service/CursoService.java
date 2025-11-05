package dev.cmartinez.service;

import dev.cmartinez.dto.CursoDTO;
import dev.cmartinez.entity.Curso;
import dev.cmartinez.entity.Categoria;
import dev.cmartinez.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private CategoriaService categoriaService;

    // Convertir Entity a DTO
    private CursoDTO convertToDTO(Curso curso) {
        CursoDTO dto = new CursoDTO();
        dto.setId(curso.getId());
        dto.setNombre(curso.getNombre());
        dto.setProfesor(curso.getProfesor());
        dto.setDescripcion(curso.getDescripcion());
        dto.setDuracionHoras(curso.getDuracionHoras());
        dto.setFechaCreacion(curso.getFechaCreacion());
        dto.setActivo(curso.getActivo());
        dto.setCategoriaId(curso.getCategoria() != null ? curso.getCategoria().getId() : null);
        return dto;
    }

    // Convertir DTO a Entity
    private Curso convertToEntity(CursoDTO cursoDTO) {
        Curso curso = new Curso();
        curso.setId(cursoDTO.getId());
        curso.setNombre(cursoDTO.getNombre());
        curso.setProfesor(cursoDTO.getProfesor());
        curso.setDescripcion(cursoDTO.getDescripcion());
        curso.setDuracionHoras(cursoDTO.getDuracionHoras());
        curso.setActivo(cursoDTO.getActivo());
        
        // Manejar relación con categoría
        if (cursoDTO.getCategoriaId() != null) {
            Optional<Categoria> categoria = categoriaService.findEntityById(cursoDTO.getCategoriaId());
            categoria.ifPresent(curso::setCategoria);
        }
        
        return curso;
    }

    // Obtener todos los cursos
    public List<CursoDTO> findAll() {
        return cursoRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Obtener curso por ID
    public Optional<CursoDTO> findById(Long id) {
        return cursoRepository.findById(id)
                .map(this::convertToDTO);
    }

    // Guardar curso (crear o actualizar)
    public CursoDTO save(CursoDTO cursoDTO) {
        Curso curso = convertToEntity(cursoDTO);
        Curso savedCurso = cursoRepository.save(curso);
        return convertToDTO(savedCurso);
    }

    // Eliminar curso
    public void deleteById(Long id) {
        cursoRepository.deleteById(id);
    }

    // Buscar cursos por nombre
    public List<CursoDTO> findByNombreContaining(String nombre) {
        return cursoRepository.findByNombreContainingIgnoreCase(nombre)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Obtener cursos activos
    public List<CursoDTO> findActiveCursos() {
        return cursoRepository.findByActivoTrue()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Obtener cursos por categoría
    public List<CursoDTO> findByCategoriaId(Long categoriaId) {
        return cursoRepository.findByCategoriaId(categoriaId)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
}
