package com.example.swift.modelos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@DynamicUpdate
@Table(name ="OPERACIONES")
public class Operaciones implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_OPERACIONES")
    private Long id;

//    @NotEmpty
    @Column(name = "REFERENCIA")
    private String referencia;

//    @NotEmpty
    @Column(name = "FECHA_VALOR")
    private String fecha_valor;

    @JoinColumn(name="FK_DIVISA",updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Divisa moneda;

    @Column(name = "MONTO")
    private BigDecimal monto;

    @Column(name = "ESTATUS")
    private int estatus;

    @Column(name = "CONTRATO_CLIENTE")
    private String contrato_cliente;

    @Column(name = "NOMBRE_CLIENTE")
    private String nombre_cliente;

    @Column(name = "DIR_CLIENTE")
    private String direccion_cliente;

    @Column(name = "BIC_ORDENANTE")
    private String bic_ordenante;

    @JoinColumn(name="FK_BIC_CORRESPONSAL",updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private CuentasNostro corresponsal;

    @Column(name = "CTA_BANCO_BNF")
    private String cta_banco_bnf;

    @Column(name = "NOMBRE_BANCO_BNF")
    private String nombre_banco_bnf;

//    @NotEmpty
    @Column(name = "CTA_BENEFICIARIO")
    private String cta_beneficiario;

    @JoinColumn(name="FK_TIPO_MT",updatable = false)
    @ManyToOne(optional = true, fetch = FetchType.EAGER)
    private TipoMensaje tipo_mensaje;

    @Column(name = "FECHA_CREADO")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyMMdd")
    private Date fecha_creado;


    @PrePersist
    public void prePersist(){
        fecha_creado = new Date();
        estatus = 8;
        tipo_mensaje = new TipoMensaje(new Long(1));
    }

}


