package org.example.vetalisbackend.scheduling.domain.model.aggregates;

import jakarta.persistence.*;
import org.example.vetalisbackend.scheduling.domain.model.enums.EstadoCita;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "citas")
@EntityListeners(AuditingEntityListener.class)
public class Cita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long mascotaId;

    @Column(nullable = false)
    private Long veterinarioId;

    private LocalDateTime fecha;
    private String motivo;
    private String tipo;

    @Enumerated(EnumType.STRING)
    private EstadoCita estado;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private Date createdAt;

    @LastModifiedDate
    @Column(nullable = false)
    private Date updatedAt;

    public Cita() {}

    public Cita(Long mascotaId, Long veterinarioId, LocalDateTime fecha, String motivo,
                String tipo, EstadoCita estado) {
        this.mascotaId = mascotaId;
        this.veterinarioId = veterinarioId;
        this.fecha = fecha;
        this.motivo = motivo;
        this.tipo = tipo;
        this.estado = estado;
    }

    public Long getId() { return id; }
    public Long getMascotaId() { return mascotaId; }
    public Long getVeterinarioId() { return veterinarioId; }
    public LocalDateTime getFecha() { return fecha; }
    public String getMotivo() { return motivo; }
    public String getTipo() { return tipo; }
    public EstadoCita getEstado() { return estado; }

    public void setMascotaId(Long mascotaId) { this.mascotaId = mascotaId; }
    public void setVeterinarioId(Long veterinarioId) { this.veterinarioId = veterinarioId; }
    public void setFecha(LocalDateTime fecha) { this.fecha = fecha; }
    public void setMotivo(String motivo) { this.motivo = motivo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public void setEstado(EstadoCita estado) { this.estado = estado; }
}
