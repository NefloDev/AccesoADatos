package Entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Pelicula {

    @JsonProperty(value = "title")
    private String titulo;
    @JsonProperty(value = "year")
    private int anyo;

    public Pelicula(PeliculaOscarizada pelicula){
        titulo = pelicula.getPelicula();
        anyo = pelicula.getAnyo();
    }

}
