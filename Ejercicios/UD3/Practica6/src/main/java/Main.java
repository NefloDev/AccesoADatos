import Entities.Pilot;

import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {
        OperacionesCRUDPilotos operaciones = new OperacionesCRUDPilotos();
        Pilot p1 = new Pilot(1, "HAM", "Lewis", "Hamilton", LocalDate.of(1985, 1, 7), "United Kingdom");
        Pilot p2 = new Pilot(1, "NEB", "Alejandro", "Nebot", LocalDate.of(2004, 1, 27), "Spain");

        System.out.println("Pilotos antes de añadir a Lewis ##########################");
        operaciones.ReadPilots().forEach(p -> System.out.println(p.getName()));
        operaciones.CreatePilot(p1);
        System.out.println("\nPilotos despues de añadir a Lewis Hamilton ##########################");
        operaciones.ReadPilots().forEach(p -> System.out.println(p.getName()));

        System.out.println("\nPiloto con ID 1 ##########################");
        System.out.println(operaciones.ReadPilot(1));

        operaciones.UpdatePilot(p2);
        System.out.println("\nPiloto con ID 1 despues de actualizarlo ##########################");
        System.out.println(operaciones.ReadPilot(1));

        operaciones.DeletePilot(p1);

        System.out.println("\nPilotos despues de eliminar a Lewis Hamilton ##########################");
        operaciones.ReadPilots().forEach(p -> System.out.println(p.getName()));

        //La base de datos no concuerda con los datos de la página que mandaste, pero igualmente (bajo mi criterio) creo que he hecho lo que se pedía

        operaciones.ShowPilotClassification();

        operaciones.ShowPilotClassificationByPoints();
    }

}
