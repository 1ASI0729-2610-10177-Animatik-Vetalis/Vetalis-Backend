package org.example.vetalisbackend.iot.domain.model.entities;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "historial_dispensacion")
@EntityListeners(AuditingEntityListener.class)
public class LogAlimentacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long dispensadorId;

    private LocalDateTime fechaHora;
    private Double cantidadServida;
    private Boolean exito;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private Date createdAt;

    @LastModifiedDate
    @Column(nullable = false)
    private Date updatedAt;

    public LogAlimentacion() {}

    public LogAlimentacion(Long dispensadorId, LocalDateTime fechaHora, Double cantidadServida, Boolean exito) {
        this.dispensadorId = dispensadorId;
        this.fechaHora = fechaHora;
        this.cantidadServida = cantidadServida;
        this.exito = exito;
    }

    public Long getId() { return id; }
    public Long getDispensadorId() { return dispensadorId; }
    public LocalDateTime getFechaHora() { return fechaHora; }
    public Double getCantidadServida() { return cantidadServida; }
    public Boolean getExito() { return exito; }

    public void setDispensadorId(Long dispensadorId) { this.dispensadorId = dispensadorId; }
    public void setFechaHora(LocalDateTime fechaHora) { this.fechaHora = fechaHora; }
    public void setCantidadServida(Double cantidadServida) { this.cantidadServida = cantidadServida; }
    public void setExito(Boolean exito) { this.exito = exito; }
}
