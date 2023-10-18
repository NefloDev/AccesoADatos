package org.ficheros.entities;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor

@JacksonXmlRootElement(localName = "lenguajes")
public class ListaLenguajes {
    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "lenguaje")
    private List<Lenguaje> lenguajes;

    public ListaLenguajes() {
    }

    public ListaLenguajes(List<Lenguaje> lenguajes) {
        this.lenguajes = lenguajes;
    }

    public void setLenguajes(List<Lenguaje> lenguajes) {
        this.lenguajes = lenguajes;
    }

    public List<Lenguaje> getLenguajes() {
        return lenguajes;
    }

    @Override
    public String toString() {
        return "ListaLenguajes{" +
                "lenguajes=" + lenguajes +
                "}";
    }
}
