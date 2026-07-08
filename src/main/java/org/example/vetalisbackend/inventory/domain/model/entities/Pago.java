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

    private Long consultaId;
    private Long mascotaId;

    private Double monto;
    private Double montoOriginal;
    private String descripcion;
    private String metodoPago;
    private String metodoPago2;
    private Double monto2;
    private LocalDateTime fechaPago;
    private String estado;

    private Long medicamentoId;
    private Integer cantidad;
    private Double descuento;

    @Column(nullable = false)
    private Boolean anulado = false;

    private String motivoAnulacion;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private Date createdAt;

    @LastModifiedDate
    @Column(nullable = false)
    private Date updatedAt;

    public Pago() {}

    public Pago(Long consultaId, Long mascotaId, Double monto, Double montoOriginal,
                String descripcion, String metodoPago, String metodoPago2, Double monto2,
                LocalDateTime fechaPago, String estado,
                Long medicamentoId, Integer cantidad, Double descuento) {
        this.consultaId = consultaId;
        this.mascotaId = mascotaId;
        this.monto = monto;
        this.montoOriginal = montoOriginal;
        this.descripcion = descripcion;
        this.metodoPago = metodoPago;
        this.metodoPago2 = metodoPago2;
        this.monto2 = monto2;
        this.fechaPago = fechaPago;
        this.estado = estado;
        this.medicamentoId = medicamentoId;
        this.cantidad = cantidad;
        this.descuento = descuento != null ? descuento : 0.0;
        this.anulado = false;
    }

    public Long getId() { return id; }
    public Long getConsultaId() { return consultaId; }
    public Long getMascotaId() { return mascotaId; }
    public Double getMonto() { return monto; }
    public Double getMontoOriginal() { return montoOriginal; }
    public String getDescripcion() { return descripcion; }
    public String getMetodoPago() { return metodoPago; }
    public String getMetodoPago2() { return metodoPago2; }
    public Double getMonto2() { return monto2; }
    public LocalDateTime getFechaPago() { return fechaPago; }
    public String getEstado() { return estado; }
    public Long getMedicamentoId() { return medicamentoId; }
    public Integer getCantidad() { return cantidad; }
    public Double getDescuento() { return descuento; }
    public Boolean getAnulado() { return anulado; }
    public String getMotivoAnulacion() { return motivoAnulacion; }

    public void setConsultaId(Long consultaId) { this.consultaId = consultaId; }
    public void setMascotaId(Long mascotaId) { this.mascotaId = mascotaId; }
    public void setMonto(Double monto) { this.monto = monto; }
    public void setMontoOriginal(Double montoOriginal) { this.montoOriginal = montoOriginal; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public void setMetodoPago(String metodoPago) { this.metodoPago = metodoPago; }
    public void setMetodoPago2(String metodoPago2) { this.metodoPago2 = metodoPago2; }
    public void setMonto2(Double monto2) { this.monto2 = monto2; }
    public void setFechaPago(LocalDateTime fechaPago) { this.fechaPago = fechaPago; }
    public void setEstado(String estado) { this.estado = estado; }
    public void setMedicamentoId(Long medicamentoId) { this.medicamentoId = medicamentoId; }
    public void setCantidad(Integer cantidad) { this.cantidad = cantidad; }
    public void setDescuento(Double descuento) { this.descuento = descuento; }
    public void setAnulado(Boolean anulado) { this.anulado = anulado; }
    public void setMotivoAnulacion(String motivoAnulacion) { this.motivoAnulacion = motivoAnulacion; }
}
