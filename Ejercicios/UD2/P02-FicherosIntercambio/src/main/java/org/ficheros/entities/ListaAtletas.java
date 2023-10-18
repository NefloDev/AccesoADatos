/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.ficheros.entities;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import java.util.List;

/**
 *
 * @author alnef
 */
@JacksonXmlRootElement(localName="atletas")
public class ListaAtletas {
    @JacksonXmlElementWrapper(useWrapping = true)
    @JacksonXmlProperty(localName="atleta")
    private List<AtletaFemenina> atletas;

    public ListaAtletas() {
    }

    public ListaAtletas(List<AtletaFemenina> lista) {
        this.atletas = lista;
    }

    public List<AtletaFemenina> getLista() {
        return atletas;
    }

    public void setLista(List<AtletaFemenina> lista) {
        this.atletas = lista;
    }
    
    @Override
    public String toString(){
        return "ListaAtletas{" +
                "atleta='" + atletas + "\'" +
                "}";
    }
    
}
