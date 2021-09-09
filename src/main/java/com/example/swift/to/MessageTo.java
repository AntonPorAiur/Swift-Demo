package com.example.swift.to;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageTo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String swift;

    private short resultado;
    private String mensaje;

}
