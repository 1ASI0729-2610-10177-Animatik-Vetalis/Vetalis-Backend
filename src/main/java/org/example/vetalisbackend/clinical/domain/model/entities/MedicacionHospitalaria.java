package org.example.vetalisbackend.clinical.domain.model.entities;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "medicacion_hospitalaria")
@EntityListeners(AuditingEntityListener.class)
public class MedicacionHospitalaria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long hospitalizacionId;

    private Long medicamentoId;
    private String dosis;
    private String frecuencia;
    private LocalDate fechaInicio;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private Date createdAt;

    @LastModifiedDate
    @Column(nullable = false)
    private Date updatedAt;

    public MedicacionHospitalaria() {}

    public MedicacionHospitalaria(Long hospitalizacionId, Long medicamentoId, String dosis,
                                  String frecuencia, LocalDate fechaInicio) {
        this.hospitalizacionId = hospitalizacionId;
        this.medicamentoId = medicamentoId;
        this.dosis = dosis;
        this.frecuencia = frecuencia;
        this.fechaInicio = fechaInicio;
    }

    public Long getId() { return id; }
    public Long getHospitalizacionId() { return hospitalizacionId; }
    public Long getMedicamentoId() { return medicamentoId; }
    public String getDosis() { return dosis; }
    public String getFrecuencia() { return frecuencia; }
    public LocalDate getFechaInicio() { return fechaInicio; }

    public void setHospitalizacionId(Long hospitalizacionId) { this.hospitalizacionId = hospitalizacionId; }
    public void setMedicamentoId(Long medicamentoId) { this.medicamentoId = medicamentoId; }
    public void setDosis(String dosis) { this.dosis = dosis; }
    public void setFrecuencia(String frecuencia) { this.frecuencia = frecuencia; }
    public void setFechaInicio(LocalDate fechaInicio) { this.fechaInicio = fechaInicio; }
}
