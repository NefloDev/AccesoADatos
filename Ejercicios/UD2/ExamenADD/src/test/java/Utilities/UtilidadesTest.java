package Utilities;

import Entities.PeliculaOscarizada;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UtilidadesTest {

    //Ejercicio 3
    @Test
    public void actoresConMasDeUnOscarUnActorTest() {
        List<PeliculaOscarizada> lista = List.of(
                new PeliculaOscarizada("1;1928;22;Janet Gaynor;Seventh Heaven, Street Angel and Sunrise: A Song of Two Humans"),
                new PeliculaOscarizada("2;1928;22;Janet Gaynor;Seventh Heaven, Coquette"),
                new PeliculaOscarizada("4;1931;63;Marie Dressler;Min and Bill")
        );
        assertEquals(1, Utilidades.actoresConMasDeUnOscar(lista).size());
    }

    @Test
    public void actoresConMasDeUnOscarDosActoresTest(){
        List<PeliculaOscarizada> lista2 = List.of(
                new PeliculaOscarizada("1;1928;22;Janet Gaynor;Seventh Heaven, Street Angel and Sunrise: A Song of Two Humans"),
                new PeliculaOscarizada("2;1928;22;Janet Gaynor;Seventh Heaven, Coquette"),
                new PeliculaOscarizada("4;1931;63;Marie Dressler;Min and Bill"),
                new PeliculaOscarizada("5;1931;63;Marie Dressler;Mario Bros Movie"),
                new PeliculaOscarizada("6;1999;99;Pepito Grillo;Torrente 10")
        );
        assertEquals(2, Utilidades.actoresConMasDeUnOscar(lista2).size());
    }
}