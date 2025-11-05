package dev.cmartinez.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;

public class CategoriaDTO {
    private Long id;
    
    @NotBlank(message = "{categoria.nombre.notblank}")
    @Size(max = 100, message = "{categoria.nombre.size}")
    private String nombre;
    
    @Size(max = 500, message = "{categoria.descripcion.size}")
    private String descripcion;
    
    private LocalDateTime fechaCreacion;
    private Boolean activo;
    
    // Constructores
    public CategoriaDTO() {}
    
    public CategoriaDTO(Long id, String nombre, String descripcion, LocalDateTime fechaCreacion, Boolean activo) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fechaCreacion = fechaCreacion;
        this.activo = activo;
    }
    
    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    
    public LocalDateTime getFechaCreacion() { return fechaCreacion; }
    public void setFechaCreacion(LocalDateTime fechaCreacion) { this.fechaCreacion = fechaCreacion; }
    
    public Boolean getActivo() { return activo; }
    public void setActivo(Boolean activo) { this.activo = activo; }
}
