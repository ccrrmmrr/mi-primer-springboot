package dev.cmartinez.controller;

import dev.cmartinez.dto.CursoDTO;
import dev.cmartinez.service.CursoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cursos")
public class CursoController {

    @Autowired
    private CursoService cursoService;

    // GET - Obtener todos los cursos
    @GetMapping
    public ResponseEntity<List<CursoDTO>> getAllCursos() {
        List<CursoDTO> cursos = cursoService.findAll();
        return ResponseEntity.ok(cursos);
    }

    // GET - Obtener curso por ID
    @GetMapping("/{id}")
    public ResponseEntity<CursoDTO> getCursoById(@PathVariable Long id) {
        return cursoService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST - Crear nuevo curso
    @PostMapping
    public ResponseEntity<CursoDTO> createCurso(@Valid @RequestBody CursoDTO cursoDTO) {
        CursoDTO savedCurso = cursoService.save(cursoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCurso);
    }

    // PUT - Actualizar curso existente
    @PutMapping("/{id}")
    public ResponseEntity<CursoDTO> updateCurso(@PathVariable Long id, 
                                               @Valid @RequestBody CursoDTO cursoDTO) {
        // Asegurar que el ID del path coincide con el ID del body
        if (!id.equals(cursoDTO.getId())) {
            return ResponseEntity.badRequest().build();
        }
        
        CursoDTO updatedCurso = cursoService.save(cursoDTO);
        return ResponseEntity.ok(updatedCurso);
    }

    // DELETE - Eliminar curso
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCurso(@PathVariable Long id) {
        if (cursoService.findById(id).isPresent()) {
            cursoService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // GET - Buscar cursos por nombre
    @GetMapping("/buscar")
    public ResponseEntity<List<CursoDTO>> searchCursos(@RequestParam String nombre) {
        List<CursoDTO> cursos = cursoService.findByNombreContaining(nombre);
        return ResponseEntity.ok(cursos);
    }

    // GET - Obtener cursos activos
    @GetMapping("/activos")
    public ResponseEntity<List<CursoDTO>> getCursosActivos() {
        List<CursoDTO> cursos = cursoService.findActiveCursos();
        return ResponseEntity.ok(cursos);
    }
}
