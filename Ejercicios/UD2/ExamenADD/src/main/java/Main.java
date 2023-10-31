import Entities.Actor;
import Entities.PeliculaOscarizada;
import Exceptions.CSVFileReadException;
import Exceptions.JsonFileWriteException;
import Exceptions.WrongSexInputException;
import Utilities.Utilidades;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        final String HOMBRE = "H";
        final String MUJER = "M";

        //EJERCICIO 1

        ArrayList<PeliculaOscarizada> lista = new ArrayList<>();
        try {
            lista.addAll(Utilidades.leerPeliculasOscarizadasCsv(HOMBRE));
            lista.addAll(Utilidades.leerPeliculasOscarizadasCsv(MUJER));
        } catch (WrongSexInputException | CSVFileReadException e) {
            System.err.println(e.getMessage());
        }

        //EJERCICIO 2

        List<Actor> actores = Utilidades.convertirPeliculasOscarizadasEnActores(lista);
        try {
            Utilidades.escribirActoresEnJson(actores);
        } catch (JsonFileWriteException e) {
            System.err.println(e.getMessage());
        }

        //Bonus

        System.out.println("Actores con mas de un oscar");
        Utilidades.actoresConMasDeUnOscar(lista).forEach(p -> System.out.print(p.getNombre() + ", "));

        System.out.println("Actores mas jovenes cuando recibieron el oscar");
        Utilidades.actoresMasJovenesEnGanarUnOscar(actores).forEach(a -> System.out.println(a.getNombre()));

    }

}
