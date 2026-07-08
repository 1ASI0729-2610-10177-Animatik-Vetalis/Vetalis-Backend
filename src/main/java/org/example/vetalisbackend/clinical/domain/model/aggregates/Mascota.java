package org.example.vetalisbackend.clinical.domain.model.aggregates;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "mascotas")
@EntityListeners(AuditingEntityListener.class)
public class Mascota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    private String sexo;

    private LocalDate fechaNacimiento;

    private Double peso;

    private String estado;

    @Column(columnDefinition = "TEXT")
    private String alergias;

    @Column(nullable = false)
    private Long clienteId;

    private Long razaId;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private Date createdAt;

    @LastModifiedDate
    @Column(nullable = false)
    private Date updatedAt;

    public Mascota() {}

    public Mascota(String nombre, String sexo, LocalDate fechaNacimiento, Double peso,
                   String estado, String alergias, Long clienteId, Long razaId) {
        this.nombre = nombre;
        this.sexo = sexo;
        this.fechaNacimiento = fechaNacimiento;
        this.peso = peso;
        this.estado = estado;
        this.alergias = alergias;
        this.clienteId = clienteId;
        this.razaId = razaId;
    }

    public Long getId() { return id; }
    public String getNombre() { return nombre; }
    public String getSexo() { return sexo; }
    public LocalDate getFechaNacimiento() { return fechaNacimiento; }
    public Double getPeso() { return peso; }
    public String getEstado() { return estado; }
    public String getAlergias() { return alergias; }
    public Long getClienteId() { return clienteId; }
    public Long getRazaId() { return razaId; }

    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setSexo(String sexo) { this.sexo = sexo; }
    public void setFechaNacimiento(LocalDate fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }
    public void setPeso(Double peso) { this.peso = peso; }
    public void setEstado(String estado) { this.estado = estado; }
    public void setAlergias(String alergias) { this.alergias = alergias; }
    public void setClienteId(Long clienteId) { this.clienteId = clienteId; }
    public void setRazaId(Long razaId) { this.razaId = razaId; }
}
