package org.example.vetalisbackend.clinical.domain.model.entities;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@Entity
@Table(name = "tratamientos")
@EntityListeners(AuditingEntityListener.class)
public class Tratamiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long consultaId;

    private Long medicamentoId;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    private String dosis;
    private String frecuencia;
    private String duracion;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private Date createdAt;

    @LastModifiedDate
    @Column(nullable = false)
    private Date updatedAt;

    public Tratamiento() {}

    public Tratamiento(Long consultaId, Long medicamentoId, String descripcion, String dosis,
                       String frecuencia, String duracion) {
        this.consultaId = consultaId;
        this.medicamentoId = medicamentoId;
        this.descripcion = descripcion;
        this.dosis = dosis;
        this.frecuencia = frecuencia;
        this.duracion = duracion;
    }

    public Long getId() { return id; }
    public Long getConsultaId() { return consultaId; }
    public Long getMedicamentoId() { return medicamentoId; }
    public String getDescripcion() { return descripcion; }
    public String getDosis() { return dosis; }
    public String getFrecuencia() { return frecuencia; }
    public String getDuracion() { return duracion; }

    public void setConsultaId(Long consultaId) { this.consultaId = consultaId; }
    public void setMedicamentoId(Long medicamentoId) { this.medicamentoId = medicamentoId; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public void setDosis(String dosis) { this.dosis = dosis; }
    public void setFrecuencia(String frecuencia) { this.frecuencia = frecuencia; }
    public void setDuracion(String duracion) { this.duracion = duracion; }
}
