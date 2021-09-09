package com.example.swift.repository.local;

import com.example.swift.modelos.Operaciones;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OperacionesRepository extends CrudRepository<Operaciones,Long> {

    Page<Operaciones> findByCriteria(int page, int pageSize, String keyword);
}
