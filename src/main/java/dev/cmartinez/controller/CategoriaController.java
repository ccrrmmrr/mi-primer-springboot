package dev.cmartinez.controller;

import dev.cmartinez.dto.CategoriaDTO;
import dev.cmartinez.dto.CursoDTO;
import dev.cmartinez.service.CategoriaService;
import dev.cmartinez.service.CursoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private CursoService cursoService;

    // GET - Obtener todas las categorías
    @GetMapping
    public ResponseEntity<List<CategoriaDTO>> getAllCategorias() {
        List<CategoriaDTO> categorias = categoriaService.findAll();
        return ResponseEntity.ok(categorias);
    }

    // GET - Obtener categoría por ID
    @GetMapping("/{id}")
    public ResponseEntity<CategoriaDTO> getCategoriaById(@PathVariable Long id) {
        return categoriaService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST - Crear nueva categoría
    @PostMapping
    public ResponseEntity<?> createCategoria(@Valid @RequestBody CategoriaDTO categoriaDTO) {
        // Validar nombre único
        if (categoriaService.existsByNombre(categoriaDTO.getNombre())) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Ya existe una categoría con el nombre: " + categoriaDTO.getNombre());
        }
        
        CategoriaDTO savedCategoria = categoriaService.save(categoriaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCategoria);
    }

    // PUT - Actualizar categoría existente
    @PutMapping("/{id}")
    public ResponseEntity<?> updateCategoria(@PathVariable Long id, 
                                            @Valid @RequestBody CategoriaDTO categoriaDTO) {
        // Asegurar que el ID del path coincide con el ID del body
        if (!id.equals(categoriaDTO.getId())) {
            return ResponseEntity.badRequest().body("ID del path no coincide con ID del body");
        }
        
        // Validar nombre único excluyendo el ID actual
        if (categoriaService.existsByNombreAndIdNot(categoriaDTO.getNombre(), id)) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Ya existe otra categoría con el nombre: " + categoriaDTO.getNombre());
        }
        
        CategoriaDTO updatedCategoria = categoriaService.save(categoriaDTO);
        return ResponseEntity.ok(updatedCategoria);
    }

    // DELETE - Eliminar categoría
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategoria(@PathVariable Long id) {
        boolean deleted = categoriaService.deleteById(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            // Verificar si existe la categoría
            if (!categoriaService.findById(id).isPresent()) {
                return ResponseEntity.notFound().build();
            } else {
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body("No se puede eliminar la categoría porque tiene cursos asociados");
            }
        }
    }

    // GET - Buscar categorías por nombre
    @GetMapping("/buscar")
    public ResponseEntity<List<CategoriaDTO>> searchCategorias(@RequestParam String nombre) {
        List<CategoriaDTO> categorias = categoriaService.findByNombreContaining(nombre);
        return ResponseEntity.ok(categorias);
    }

    // GET - Obtener categorías activas
    @GetMapping("/activas")
    public ResponseEntity<List<CategoriaDTO>> getCategoriasActivas() {
        List<CategoriaDTO> categorias = categoriaService.findActiveCategorias();
        return ResponseEntity.ok(categorias);
    }

    // GET - Obtener cursos por categoría
    @GetMapping("/{id}/cursos")
    public ResponseEntity<List<CursoDTO>> getCursosByCategoria(@PathVariable Long id) {
        if (!categoriaService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        
        List<CursoDTO> cursos = cursoService.findByCategoriaId(id);
        return ResponseEntity.ok(cursos);
    }
}
