package org.example.vetalisbackend.inventory.domain.model.aggregates;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@Entity
@Table(name = "medicamentos")
@EntityListeners(AuditingEntityListener.class)
public class Medicamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    private Double precioUnitario;
    private Integer stockActual;
    private Integer puntoReorden;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private Date createdAt;

    @LastModifiedDate
    @Column(nullable = false)
    private Date updatedAt;

    public Medicamento() {}

    public Medicamento(String nombre, String descripcion, Double precioUnitario,
                       Integer stockActual, Integer puntoReorden) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precioUnitario = precioUnitario;
        this.stockActual = stockActual;
        this.puntoReorden = puntoReorden;
    }

    public Long getId() { return id; }
    public String getNombre() { return nombre; }
    public String getDescripcion() { return descripcion; }
    public Double getPrecioUnitario() { return precioUnitario; }
    public Integer getStockActual() { return stockActual; }
    public Integer getPuntoReorden() { return puntoReorden; }

    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public void setPrecioUnitario(Double precioUnitario) { this.precioUnitario = precioUnitario; }
    public void setStockActual(Integer stockActual) { this.stockActual = stockActual; }
    public void setPuntoReorden(Integer puntoReorden) { this.puntoReorden = puntoReorden; }
}
