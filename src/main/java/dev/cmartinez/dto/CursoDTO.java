package dev.cmartinez.dto;

import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

public class CursoDTO {
    private Long id;
    
    @NotBlank(message = "{curso.nombre.notblank}")
    @Size(max = 100, message = "{curso.nombre.size}")
    private String nombre;
    
    @NotBlank(message = "{curso.profesor.notblank}")
    @Size(max = 100, message = "{curso.profesor.size}")
    private String profesor;
    
    @Size(max = 500, message = "{curso.descripcion.size}")
    private String descripcion;
    
    @Min(value = 1, message = "{curso.duracion.min}")
    private Integer duracionHoras;
    
    private LocalDateTime fechaCreacion;
    private Boolean activo;
    
    private Long categoriaId;
    
    // Solo constructor sin parámetros
    public CursoDTO() {}
    
    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    
    public String getProfesor() { return profesor; }
    public void setProfesor(String profesor) { this.profesor = profesor; }
    
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    
    public Integer getDuracionHoras() { return duracionHoras; }
    public void setDuracionHoras(Integer duracionHoras) { this.duracionHoras = duracionHoras; }
    
    public LocalDateTime getFechaCreacion() { return fechaCreacion; }
    public void setFechaCreacion(LocalDateTime fechaCreacion) { this.fechaCreacion = fechaCreacion; }
    
    public Boolean getActivo() { return activo; }
    public void setActivo(Boolean activo) { this.activo = activo; }
    
    public Long getCategoriaId() { return categoriaId; }
    public void setCategoriaId(Long categoriaId) { this.categoriaId = categoriaId; }
}
