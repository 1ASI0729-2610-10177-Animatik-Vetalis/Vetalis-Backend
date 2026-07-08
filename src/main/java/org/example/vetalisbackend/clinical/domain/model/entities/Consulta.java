package org.example.vetalisbackend.clinical.domain.model.entities;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "consultas")
@EntityListeners(AuditingEntityListener.class)
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long mascotaId;

    @Column(nullable = false)
    private Long veterinarioId;

    private LocalDateTime fecha;

    private String tipo;

    @Column(columnDefinition = "TEXT")
    private String subjetivo;

    @Column(columnDefinition = "TEXT")
    private String objetivo;

    @Column(columnDefinition = "TEXT")
    private String evaluacion;

    @Column(columnDefinition = "TEXT")
    private String plan;

    private String estado;

    @Column(columnDefinition = "TEXT")
    private String diagnostico;

    private Double temperatura;

    @Column(nullable = false)
    private Boolean cerrada = false;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private Date createdAt;

    @LastModifiedDate
    @Column(nullable = false)
    private Date updatedAt;

    public Consulta() {}

    public Consulta(Long mascotaId, Long veterinarioId, LocalDateTime fecha, String tipo,
                    String subjetivo, String objetivo, String evaluacion, String plan,
                    String estado, String diagnostico, Double temperatura) {
        this.mascotaId = mascotaId;
        this.veterinarioId = veterinarioId;
        this.fecha = fecha;
        this.tipo = tipo;
        this.subjetivo = subjetivo;
        this.objetivo = objetivo;
        this.evaluacion = evaluacion;
        this.plan = plan;
        this.estado = estado;
        this.diagnostico = diagnostico;
        this.temperatura = temperatura;
        this.cerrada = false;
    }

    public Long getId() { return id; }
    public Long getMascotaId() { return mascotaId; }
    public Long getVeterinarioId() { return veterinarioId; }
    public LocalDateTime getFecha() { return fecha; }
    public String getTipo() { return tipo; }
    public String getSubjetivo() { return subjetivo; }
    public String getObjetivo() { return objetivo; }
    public String getEvaluacion() { return evaluacion; }
    public String getPlan() { return plan; }
    public String getEstado() { return estado; }
    public String getDiagnostico() { return diagnostico; }
    public Double getTemperatura() { return temperatura; }
    public Boolean getCerrada() { return cerrada; }

    public void setMascotaId(Long mascotaId) { this.mascotaId = mascotaId; }
    public void setVeterinarioId(Long veterinarioId) { this.veterinarioId = veterinarioId; }
    public void setFecha(LocalDateTime fecha) { this.fecha = fecha; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public void setSubjetivo(String subjetivo) { this.subjetivo = subjetivo; }
    public void setObjetivo(String objetivo) { this.objetivo = objetivo; }
    public void setEvaluacion(String evaluacion) { this.evaluacion = evaluacion; }
    public void setPlan(String plan) { this.plan = plan; }
    public void setEstado(String estado) { this.estado = estado; }
    public void setDiagnostico(String diagnostico) { this.diagnostico = diagnostico; }
    public void setTemperatura(Double temperatura) { this.temperatura = temperatura; }
    public void setCerrada(Boolean cerrada) { this.cerrada = cerrada; }
}
