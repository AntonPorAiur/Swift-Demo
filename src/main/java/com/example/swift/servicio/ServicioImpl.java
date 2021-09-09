package com.example.swift.servicio;

import com.example.swift.modelos.CuentasNostro;
import com.example.swift.modelos.Divisa;
import com.example.swift.modelos.Operaciones;
import com.example.swift.modelos.dao.CuentasNostroDao;
import com.example.swift.modelos.dao.DivisaDao;
import com.example.swift.modelos.dao.OperacionesDao;
import com.example.swift.repository.local.OperacionesRepository;
import com.example.swift.utilidades.local.MessageBuilderLocal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;


@Service
public class ServicioImpl implements IServicio {

    @Autowired
    private OperacionesDao operacionesDao;

    @Autowired
    private OperacionesRepository operacionesRepository;

    @Autowired
    private DivisaDao divisaDao;

    @Autowired
    private CuentasNostroDao cuentasNostroDao;

    @Autowired
    private MessageBuilderLocal messageBuilder;


    @Override
    @Transactional(readOnly = true)
    public List<Operaciones> findAll() {
        return operacionesDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Operaciones> findAllPageable(Pageable pageable) {
        return operacionesDao.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Operaciones> findByCriteria(int page, int pageSize, String keyword) {
        return operacionesRepository.findByCriteria(page,pageSize,keyword);
    }

    @Override
    @Transactional(readOnly = true)
    public Operaciones findOne(Long id) {
        return operacionesDao.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void eliminarOperacion(Long id){
        operacionesDao.deleteById(id);
    }

    @Override
    @Transactional(readOnly = false)
    public void save(Operaciones operacion) {
        operacionesDao.save(operacion);
    }

    @Override
    public List<Divisa> findAllDivisas() {
        return divisaDao.findAll();
    }

    @Override
    public List<CuentasNostro> findAllCorresponsales() { return cuentasNostroDao.findAll(); }

    @Override
    public String getMessageBuilder(int[] ops) {

        String message = "";
        for(int i: ops){

            Operaciones op = this.findOne(new Long(i));

             messageBuilder.blocksFormer(op);
        }

        return message;
    }
}
