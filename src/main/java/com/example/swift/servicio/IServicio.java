package com.example.swift.servicio;

import com.example.swift.modelos.CuentasNostro;
import com.example.swift.modelos.Divisa;
import com.example.swift.modelos.Operaciones;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IServicio {

    @Transactional(readOnly = true)
    List<Operaciones> findAll();

    @Transactional(readOnly = true)
    public Page<Operaciones> findAllPageable(Pageable pageable);

    @Transactional(readOnly = true)
    Page<Operaciones> findByCriteria(int page, int pageSize, String keyword);

    @Transactional(readOnly = true)
    Operaciones findOne(Long id);

    @Transactional
    void eliminarOperacion(Long id);

    public void save(Operaciones operacion);

    public List<Divisa> findAllDivisas();

    public List<CuentasNostro> findAllCorresponsales();

    String getMessageBuilder(int[] ops);
}
