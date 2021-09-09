package com.example.swift.utilidades;

import lombok.Getter;
import org.springframework.data.domain.Page;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter
public class PageRender<T> {

    private String url;
    private Page<T> page;
    private int totalPaginas;
    private int numElementosPorPagina;
    private int paginaActual;
    private List<ItemPage> paginas;

    //Filter values
    private String keyword;
//    private String referencia;
//    private String corresponsal;

    public PageRender(String url, Page<T> page, String keyword) {
        //Seteando los valores de los filtros actuales para retornarlos a la vista
        this.keyword = keyword;
//        this.referencia = referencia;
//        this.corresponsal = corresponsal;

        this.url = url;
        this.page = page;
        this.paginas = new ArrayList<>(Collections.emptyList());
        this.numElementosPorPagina = page.getSize();
        this.totalPaginas = page.getTotalPages();
        this.paginaActual = page.getNumber() + 1;

        int desde, hasta;

        if(totalPaginas <= numElementosPorPagina/2) {
            desde = 1;
            hasta = totalPaginas;
        }else if(paginaActual >= totalPaginas - numElementosPorPagina/2){
            desde = totalPaginas - numElementosPorPagina + 1;
            hasta = numElementosPorPagina;
        }else{
            desde =paginaActual - numElementosPorPagina/2;
            hasta = numElementosPorPagina;
        }

        for(int i=0; i < hasta; i++) {
            paginas.add(new ItemPage(desde + i, paginaActual == desde + i));
        }
    }

    public boolean isFirst(){
        return page.isFirst();
    }

    public boolean isLast(){
        return page.isLast();
    }

    public boolean isHasNext(){
        return page.hasNext();
    }

    public boolean isHasPrevious(){
        return page.hasPrevious();
    }

}
