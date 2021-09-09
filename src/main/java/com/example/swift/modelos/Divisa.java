package com.example.swift.modelos;

import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "DIVISA")
public class Divisa implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_DIVISA")
    private Long id;

    @Column(name = "ISO")
    private String iso;

    @Column(name = "NOMBRE")
    private String nombre;

}
