package org.example.vetalisbackend.iot.domain.model.aggregates;

import jakarta.persistence.*;
import org.example.vetalisbackend.iot.domain.model.enums.EstadoDispensador;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "dispensadores_iot")
@EntityListeners(AuditingEntityListener.class)
public class DispensadorIoT {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String numeroSerie;

    private String modelo;

    @Enumerated(EnumType.STRING)
    private EstadoDispensador estado;

    private Double nivelAlimento;
    private LocalDateTime ultimaConexion;
    private Long mascotaId;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private Date createdAt;

    @LastModifiedDate
    @Column(nullable = false)
    private Date updatedAt;

    public DispensadorIoT() {}

    public DispensadorIoT(String numeroSerie, String modelo, EstadoDispensador estado,
                          Double nivelAlimento, LocalDateTime ultimaConexion) {
        this.numeroSerie = numeroSerie;
        this.modelo = modelo;
        this.estado = estado;
        this.nivelAlimento = nivelAlimento;
        this.ultimaConexion = ultimaConexion;
    }

    public Long getId() { return id; }
    public String getNumeroSerie() { return numeroSerie; }
    public String getModelo() { return modelo; }
    public EstadoDispensador getEstado() { return estado; }
    public Double getNivelAlimento() { return nivelAlimento; }
    public LocalDateTime getUltimaConexion() { return ultimaConexion; }

    public void setNumeroSerie(String numeroSerie) { this.numeroSerie = numeroSerie; }
    public void setModelo(String modelo) { this.modelo = modelo; }
    public void setEstado(EstadoDispensador estado) { this.estado = estado; }
    public void setNivelAlimento(Double nivelAlimento) { this.nivelAlimento = nivelAlimento; }
    public void setUltimaConexion(LocalDateTime ultimaConexion) { this.ultimaConexion = ultimaConexion; }
    public Long getMascotaId() { return mascotaId; }
    public void setMascotaId(Long mascotaId) { this.mascotaId = mascotaId; }
}
