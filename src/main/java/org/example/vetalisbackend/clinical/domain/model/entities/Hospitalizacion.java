package org.example.vetalisbackend.clinical.domain.model.entities;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "hospitalizacion")
@EntityListeners(AuditingEntityListener.class)
public class Hospitalizacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long mascotaId;

    private LocalDateTime fechaIngreso;
    private LocalDateTime fechaSalida;

    @Column(columnDefinition = "TEXT")
    private String motivo;

    @Column(columnDefinition = "TEXT")
    private String diagnostico;

    @Column(columnDefinition = "TEXT")
    private String tratamiento;

    private String estado;
    private Long veterinarioId;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private Date createdAt;

    @LastModifiedDate
    @Column(nullable = false)
    private Date updatedAt;

    public Hospitalizacion() {}

    public Hospitalizacion(Long mascotaId, LocalDateTime fechaIngreso, LocalDateTime fechaSalida,
                           String motivo, String diagnostico, String tratamiento,
                           String estado, Long veterinarioId) {
        this.mascotaId = mascotaId;
        this.fechaIngreso = fechaIngreso;
        this.fechaSalida = fechaSalida;
        this.motivo = motivo;
        this.diagnostico = diagnostico;
        this.tratamiento = tratamiento;
        this.estado = estado;
        this.veterinarioId = veterinarioId;
    }

    public Long getId() { return id; }
    public Long getMascotaId() { return mascotaId; }
    public LocalDateTime getFechaIngreso() { return fechaIngreso; }
    public LocalDateTime getFechaSalida() { return fechaSalida; }
    public String getMotivo() { return motivo; }
    public String getDiagnostico() { return diagnostico; }
    public String getTratamiento() { return tratamiento; }
    public String getEstado() { return estado; }
    public Long getVeterinarioId() { return veterinarioId; }

    public void setMascotaId(Long mascotaId) { this.mascotaId = mascotaId; }
    public void setFechaIngreso(LocalDateTime fechaIngreso) { this.fechaIngreso = fechaIngreso; }
    public void setFechaSalida(LocalDateTime fechaSalida) { this.fechaSalida = fechaSalida; }
    public void setMotivo(String motivo) { this.motivo = motivo; }
    public void setDiagnostico(String diagnostico) { this.diagnostico = diagnostico; }
    public void setTratamiento(String tratamiento) { this.tratamiento = tratamiento; }
    public void setEstado(String estado) { this.estado = estado; }
    public void setVeterinarioId(Long veterinarioId) { this.veterinarioId = veterinarioId; }
}
