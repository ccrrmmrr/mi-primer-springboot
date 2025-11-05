package dev.cmartinez.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "cursos")
public class Curso {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, length = 100)
    private String nombre;
    
    @Column(name = "profesor", nullable = false, length = 100)
    private String profesor;
    
    @Column(name = "descripcion", length = 500)
    private String descripcion;
    
    @Column(name = "duracion_horas")
    private Integer duracionHoras;
    
    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;
    
    @Column(name = "activo")
    private Boolean activo = true;
    
    // Relación ManyToOne con Categoria
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;
    
    // Constructores
    public Curso() {
        this.fechaCreacion = LocalDateTime.now();
    }
    
    public Curso(String nombre, String profesor, String descripcion, Integer duracionHoras) {
        this();
        this.nombre = nombre;
        this.profesor = profesor;
        this.descripcion = descripcion;
        this.duracionHoras = duracionHoras;
    }
    
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
    
    public Categoria getCategoria() { return categoria; }
    public void setCategoria(Categoria categoria) { this.categoria = categoria; }
    
    @Override
    public String toString() {
        return "Curso{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", profesor='" + profesor + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", duracionHoras=" + duracionHoras +
                ", fechaCreacion=" + fechaCreacion +
                ", activo=" + activo +
                ", categoria=" + (categoria != null ? categoria.getNombre() : "null") +
                '}';
    }
}
