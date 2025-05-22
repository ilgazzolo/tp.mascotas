package com.miapp.Parcial.model;

import com.miapp.Parcial.enums.EstadoMascota;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Entity
@Table(name = "mascotas_reportadas")
public class MascotaReportada {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "el nombre es obligatorio")
    @Min(value = 3)
    @Column(nullable = false)
    private String nombre;

    @NotNull
    @Column(nullable = false)
    private String tipo;

    @NotNull
    @Column(nullable = false)
    private String color;

    @NotNull
    @Column(nullable = false)
    private String zona;

    private LocalDate fechaReporte;

    @Enumerated(EnumType.STRING)
    private EstadoMascota estado;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "reportante_id", nullable = false)
    private Reportante reportante;

    public MascotaReportada(Long id, String nombre, String tipo, String color, String zona, LocalDate fechaReporte, Reportante reportante) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.color = color;
        this.zona = zona;
        this.fechaReporte = fechaReporte;
        this.estado = EstadoMascota.ACTIVO;
        this.reportante = reportante;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }

    public LocalDate getFechaReporte() {
        return fechaReporte;
    }

    public void setFechaReporte(LocalDate fechaReporte) {
        this.fechaReporte = fechaReporte;
    }

    public EstadoMascota getEstado() {
        return estado;
    }

    public void setEstado(EstadoMascota estado) {
        this.estado = estado;
    }

    public Reportante getReportante() {
        return reportante;
    }

    public void setReportante(Reportante reportante) {
        this.reportante = reportante;
    }
}
