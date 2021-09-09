package com.example.swift.modelos;

import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "CUENTAS_NOSTRO")
public class CuentasNostro implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CUENTAS_NOSTRO")
    private Long id;

    @Column(name = "BIC")
    private String bic;

    @Column(name = "BANCO")
    private String banco;

    @Column(name = "CUENTA")
    private String cuenta;

    @Column(name = "DIRECCION")
    private String direccion;

}
