package Utilities;

import Entities.Actor;
import Entities.PeliculaOscarizada;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UtilidadesTest {

    //Ejercicio 3
    @Test
    void actoresConMasDeUnOscarTest() {
        List<PeliculaOscarizada> lista = List.of(
                new PeliculaOscarizada("1;1928;22;Janet Gaynor;Seventh Heaven, Street Angel and Sunrise: A Song of Two Humans"),
                new PeliculaOscarizada("2;1928;22;Janet Gaynor;Seventh Heaven, Coquette"),
                new PeliculaOscarizada("3;1928;22;Janet Gaynor;Seventh Heaven, The Divorcee"),
                new PeliculaOscarizada("4;1931;63;Marie Dressler;Min and Bill")
        );
        List<Actor> actoresMasDeUnOscar = List.of(
                new Actor(new PeliculaOscarizada("1;1928;22;Janet Gaynor;Seventh Heaven, Street Angel and Sunrise: A Song of Two Humans"))
        );
        assertEquals(actoresMasDeUnOscar.stream().map(Actor::getNombre).toList(), Utilidades.actoresConMasDeUnOscar(lista).stream().map(Actor::getNombre).toList());
    }
}