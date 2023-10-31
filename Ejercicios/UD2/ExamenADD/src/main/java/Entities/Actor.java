package Entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class Actor {

    @JsonProperty(value = "name")
    private String nombre;
    @JsonProperty(value = "sex")
    private String sexo; // H - Hombre || M - Mujer
    @JsonProperty(value = "yearOfBirth")
    private int anyoNacimiento;
    @JsonProperty(value = "movies")
    private List<Pelicula> peliculas;

    public Actor(PeliculaOscarizada pelicula){
        nombre = pelicula.getActor();
        sexo = pelicula.getSexo();
        anyoNacimiento = pelicula.getAnyo() - pelicula.getEdad();
    }

}
