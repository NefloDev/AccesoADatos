package org.ficheros;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Stream;

public class TratamientoCSV {

    public static void main(String[] args) {

        try (Stream<String> contenidoFichero = Files.lines(Path.of(".", "src", "main", "resources", "funkos.csv"))) {
            List<Funko> funkoList = contenidoFichero.skip(1).map(Funko::new).toList();

            Backup.serialize(funkoList);
            funkoList = Backup.deserialize();

            System.out.println("#################### Most expensive funko: " +
                    OperacionesCSV.mostExpensiveFunko(funkoList).getName() + " (" + OperacionesCSV.mostExpensiveFunko(funkoList).getCode() + ")");

            System.out.println("\n#################### Total prices median: " + String.format("%.2f", OperacionesCSV.medianPrice(funkoList)) + "€");

            System.out.println("\n#################### Funkos by model ####################");
            for (Entry<String, List<Funko>> entry: OperacionesCSV.funkosByModel(funkoList).entrySet()) {
                for (Funko f: entry.getValue()) {
                    System.out.println(entry.getKey() + " - " + f.getName() + " (" + f.getCode() + ")");
                }
                System.out.print("\n");
            }

            System.out.println("#################### Amount of funkos by model ####################");
            OperacionesCSV.amountOfFunkosByModel(funkoList);

            System.out.println("\n#################### Funkos released in 2023 ####################");
            OperacionesCSV.funkosReleasedIn2023(funkoList).forEach(f -> System.out.println(f.getName() + " (" + f.getCode() + ")"));
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
    }
}
