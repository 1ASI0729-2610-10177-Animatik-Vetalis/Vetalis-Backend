package org.example.vetalisbackend.clinical.domain.model.entities;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "vacunas")
@EntityListeners(AuditingEntityListener.class)
public class Vacuna {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long mascotaId;

    private Long tipoVacunaId;

    private String nombreVacuna;
    private String lote;
    private LocalDate fechaAplicacion;
    private LocalDate proximaDosis;
    private String estado;

    private Long veterinarioId;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private Date createdAt;

    @LastModifiedDate
    @Column(nullable = false)
    private Date updatedAt;

    public Vacuna() {}

    public Vacuna(Long mascotaId, Long tipoVacunaId, String nombreVacuna, String lote,
                  LocalDate fechaAplicacion, LocalDate proximaDosis, String estado, Long veterinarioId) {
        this.mascotaId = mascotaId;
        this.tipoVacunaId = tipoVacunaId;
        this.nombreVacuna = nombreVacuna;
        this.lote = lote;
        this.fechaAplicacion = fechaAplicacion;
        this.proximaDosis = proximaDosis;
        this.estado = estado;
        this.veterinarioId = veterinarioId;
    }

    public Long getId() { return id; }
    public Long getMascotaId() { return mascotaId; }
    public Long getTipoVacunaId() { return tipoVacunaId; }
    public String getNombreVacuna() { return nombreVacuna; }
    public String getLote() { return lote; }
    public LocalDate getFechaAplicacion() { return fechaAplicacion; }
    public LocalDate getProximaDosis() { return proximaDosis; }
    public String getEstado() { return estado; }
    public Long getVeterinarioId() { return veterinarioId; }

    public void setMascotaId(Long mascotaId) { this.mascotaId = mascotaId; }
    public void setTipoVacunaId(Long tipoVacunaId) { this.tipoVacunaId = tipoVacunaId; }
    public void setNombreVacuna(String nombreVacuna) { this.nombreVacuna = nombreVacuna; }
    public void setLote(String lote) { this.lote = lote; }
    public void setFechaAplicacion(LocalDate fechaAplicacion) { this.fechaAplicacion = fechaAplicacion; }
    public void setProximaDosis(LocalDate proximaDosis) { this.proximaDosis = proximaDosis; }
    public void setEstado(String estado) { this.estado = estado; }
    public void setVeterinarioId(Long veterinarioId) { this.veterinarioId = veterinarioId; }
}
