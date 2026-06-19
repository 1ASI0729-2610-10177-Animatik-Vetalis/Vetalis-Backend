package org.example.vetalisbackend.inventory.domain.model.entities;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "pagos")
@EntityListeners(AuditingEntityListener.class)
public class Pago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long consultaId;

    private Double monto;
    private String metodoPago;
    private LocalDateTime fechaPago;
    private String estado;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private Date createdAt;

    @LastModifiedDate
    @Column(nullable = false)
    private Date updatedAt;

    public Pago() {}

    public Pago(Long consultaId, Double monto, String metodoPago, LocalDateTime fechaPago, String estado) {
        this.consultaId = consultaId;
        this.monto = monto;
        this.metodoPago = metodoPago;
        this.fechaPago = fechaPago;
        this.estado = estado;
    }

    public Long getId() { return id; }
    public Long getConsultaId() { return consultaId; }
    public Double getMonto() { return monto; }
    public String getMetodoPago() { return metodoPago; }
    public LocalDateTime getFechaPago() { return fechaPago; }
    public String getEstado() { return estado; }

    public void setConsultaId(Long consultaId) { this.consultaId = consultaId; }
    public void setMonto(Double monto) { this.monto = monto; }
    public void setMetodoPago(String metodoPago) { this.metodoPago = metodoPago; }
    public void setFechaPago(LocalDateTime fechaPago) { this.fechaPago = fechaPago; }
    public void setEstado(String estado) { this.estado = estado; }
}
