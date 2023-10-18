package org.ficheros.entities;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import java.util.List;

@JacksonXmlRootElement(localName="atleta")
public class AtletaFemenina {
    @JacksonXmlProperty(isAttribute = true)
    private String nombre;
    private int edad;
    private String pais;
    @JacksonXmlElementWrapper(localName = "pruebas")
    @JacksonXmlProperty(localName = "prueba")
    private List<String> prueba;

    public AtletaFemenina(String nombre, int edad, String pais, List<String> prueba) {
        this.nombre = nombre;
        this.prueba = prueba;
        this.edad = edad;
        this.pais = pais;
    }

    public AtletaFemenina() {
    }

    public String getNombre() {
        return nombre;
    }

    public List<String> getPrueba() {
        return prueba;
    }

    public int getEdad() {
        return edad;
    }

    public String getPais() {
        return pais;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPrueba(List<String> prueba) {
        this.prueba = prueba;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }
    
    private String getPruebas(){
        StringBuilder sb = new StringBuilder();
        for (String s : prueba) {
            sb.append("\n\t\t").append("<prueba>").append(s).append("</prueba>");
        }
        return sb.toString();
    }
    
    @Override
    public String toString(){
        return "<AtletaFemenina>" +
                "\n\t<nombre>" + nombre + "</nombre>" +
                ",\n\t<edad>" + edad + "</edad>" +
                ",\n\t<pais>" + pais + "</pais>" +
                ",\n\t<pruebas>" + getPruebas() + "\n\t</pruebas>" +
                "\n</AtletaFemenina>\n";
    }
    
}
