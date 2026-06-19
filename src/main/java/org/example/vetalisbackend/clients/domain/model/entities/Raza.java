package org.example.vetalisbackend.clients.domain.model.entities;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@Entity
@Table(name = "razas")
@EntityListeners(AuditingEntityListener.class)
public class Raza {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private Long especieId;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private Date createdAt;

    @LastModifiedDate
    @Column(nullable = false)
    private Date updatedAt;

    public Raza() {}

    public Raza(String nombre, Long especieId) {
        this.nombre = nombre;
        this.especieId = especieId;
    }

    public Long getId() { return id; }
    public String getNombre() { return nombre; }
    public Long getEspecieId() { return especieId; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setEspecieId(Long especieId) { this.especieId = especieId; }
}
