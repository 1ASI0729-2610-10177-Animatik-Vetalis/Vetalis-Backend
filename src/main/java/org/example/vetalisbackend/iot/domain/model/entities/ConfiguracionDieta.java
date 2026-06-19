package org.example.vetalisbackend.iot.domain.model.entities;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@Entity
@Table(name = "configuracion_dieta_iot")
@EntityListeners(AuditingEntityListener.class)
public class ConfiguracionDieta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long hospitalizacionId;

    @Column(nullable = false)
    private Long dispensadorId;

    private Double gramosPorPorcion;
    private Integer frecuenciaHoras;
    private Boolean activo;

    @Column(columnDefinition = "TEXT")
    private String notasMedica;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private Date createdAt;

    @LastModifiedDate
    @Column(nullable = false)
    private Date updatedAt;

    public ConfiguracionDieta() {}

    public ConfiguracionDieta(Long hospitalizacionId, Long dispensadorId, Double gramosPorPorcion,
                              Integer frecuenciaHoras, Boolean activo, String notasMedica) {
        this.hospitalizacionId = hospitalizacionId;
        this.dispensadorId = dispensadorId;
        this.gramosPorPorcion = gramosPorPorcion;
        this.frecuenciaHoras = frecuenciaHoras;
        this.activo = activo;
        this.notasMedica = notasMedica;
    }

    public Long getId() { return id; }
    public Long getHospitalizacionId() { return hospitalizacionId; }
    public Long getDispensadorId() { return dispensadorId; }
    public Double getGramosPorPorcion() { return gramosPorPorcion; }
    public Integer getFrecuenciaHoras() { return frecuenciaHoras; }
    public Boolean getActivo() { return activo; }
    public String getNotasMedica() { return notasMedica; }

    public void setHospitalizacionId(Long hospitalizacionId) { this.hospitalizacionId = hospitalizacionId; }
    public void setDispensadorId(Long dispensadorId) { this.dispensadorId = dispensadorId; }
    public void setGramosPorPorcion(Double gramosPorPorcion) { this.gramosPorPorcion = gramosPorPorcion; }
    public void setFrecuenciaHoras(Integer frecuenciaHoras) { this.frecuenciaHoras = frecuenciaHoras; }
    public void setActivo(Boolean activo) { this.activo = activo; }
    public void setNotasMedica(String notasMedica) { this.notasMedica = notasMedica; }
}
