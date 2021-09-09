package com.example.swift.repository;


import com.example.swift.modelos.Operaciones;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Repository
@NoArgsConstructor
@AllArgsConstructor
public class OperacionesRepositoryImpl {

    @PersistenceContext
    private EntityManager em;

    private CriteriaBuilder cb;

    public OperacionesRepositoryImpl(EntityManager em){
        this.em = em;
//        this.cb = em.getCriteriaBuilder();
    }

    //Para modificar y agregar parametros fechas, sería agregar los param en el método y agregarlos en los Predicate
    // para que se consideren en el filtrado     
    public Page<Operaciones> findByCriteria(int page, int pageSize, String keyword) {

        //CRITERIa
        this.cb = em.getCriteriaBuilder();
        CriteriaQuery<Operaciones> cq = cb.createQuery(Operaciones.class);
        Root<Operaciones> operacionRoot = cq.from(Operaciones.class);
        //PREDICATE
        Predicate predicate = getPredicate(keyword, operacionRoot, "", "");
        //WHERE CLAUSE
        cq.where(predicate);
        //TYPED QUERY
        TypedQuery<Operaciones> query = em.createQuery(cq);
        query.setFirstResult(page * pageSize);
        query.setMaxResults(pageSize);

        //Instancia el Pageable con los parametros page y pageSize (se puede agregar el sorting)
        Pageable pageable = getPageable(page,pageSize);
        long opsCuenta = getCuentaOperaciones(predicate);

        return new PageImpl<>(query.getResultList(),pageable, opsCuenta);
    }

    public Predicate getPredicate(String keyword, Root<Operaciones> operacionRoot, String fechaInicio, String fechaFinal) {

        List<Predicate> predicate = new ArrayList<>(Collections.emptyList());
        //Aquí se van agregando los predicates que se consideren
        if(Objects.nonNull(keyword)) {
            predicate.add(
                    cb.like(
                            cb.lower(
                                    operacionRoot.get("referencia")
                            ),"%" + keyword.toLowerCase() + "%"
                    )
            );

            predicate.add(
                    cb.like(
                            cb.lower(
                                    operacionRoot.get("contrato_cliente")
                            ),"%" + keyword.toLowerCase() + "%"
                    )
            );

            predicate.add(
                    cb.like(
                            cb.lower(
                                    operacionRoot.get("corresponsal").get("bic")
                            ),"%" + keyword.toLowerCase() + "%"
                    )
            );
        }

        if( !fechaInicio.equals("") && !fechaFinal.equals("") ) {
                predicate.add(
                        cb.between(
                                operacionRoot.get("fecha_creado"), fechaInicio, fechaFinal)
                );
        }

        return cb.or(predicate.toArray(new Predicate[0]));
    }

    private Pageable getPageable(int page, int pageSize) {
        return PageRequest.of(page,pageSize);
    }

    private long getCuentaOperaciones(Predicate predicate) {
        CriteriaQuery<Long> countQuery = this.cb.createQuery(Long.class);
        Root<Operaciones> countRoot = countQuery.from(Operaciones.class);
        countQuery.select(this.cb.count(countRoot)).where(predicate);

        return em.createQuery(countQuery).getSingleResult();
    }
}
