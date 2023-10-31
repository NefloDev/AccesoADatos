package Entities;

import Utilities.Utilidades;
import lombok.Data;

@Data
public class PeliculaOscarizada {

    private static final int YEARARRAYPOSITION = 1;
    private static final int AGEARRAYPOSITION = 2;
    private static final int ACTORARRAYPOSITION = 3;
    private static final int MOVIEARRAYPOSITION = 4;

    private String pelicula;
    private int anyo;
    private String actor;
    private int edad;
    private String sexo; // H - Hombre || M - Mujer

    public PeliculaOscarizada(String desc){
        String[] array = desc.split(Utilidades.CSVSEPARATOR);

        anyo = Integer.parseInt(array[YEARARRAYPOSITION]);
        edad = Integer.parseInt(array[AGEARRAYPOSITION]);
        actor = array[ACTORARRAYPOSITION];
        pelicula = array[MOVIEARRAYPOSITION];
    }

}
