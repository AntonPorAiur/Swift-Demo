package com.example.swift.modelos.dao;

import com.example.swift.modelos.Operaciones;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import java.util.List;

public interface OperacionesDao extends JpaRepository<Operaciones,Long> {

//    public List<Operaciones> findAll();
}
