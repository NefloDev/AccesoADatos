import Entities.Pilot;

import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {
        OperacionesCRUD operaciones = new OperacionesCRUD();
        Pilot p1 = new Pilot(1, "HAM", "Lewis", "Hamilton", LocalDate.of(1985, 1, 7), "United Kingdom", operaciones.getConstructors().getConstructorById(1));
        Pilot p2 = new Pilot(1, "NEB", "Alejandro", "Nebot", LocalDate.of(2004, 1, 27), "Spain", operaciones.getConstructors().getConstructorById(1));

        System.out.println("Pilotos antes de añadir a Lewis ##########################");
        operaciones.getPilots().readPilots().forEach(p -> System.out.println(p.getName()));
        operaciones.getPilots().createPilot(p1);

        System.out.println("\nPilotos despues de añadir a Lewis Hamilton ##########################");
        operaciones.getPilots().readPilots().forEach(p -> System.out.println(p.getName()));

        System.out.println("\nPiloto con ID 1 ##########################");
        System.out.println(operaciones.getPilots().readPilot(1));

        operaciones.getPilots().updatePilot(p2);
        System.out.println("\nPiloto con ID 1 despues de actualizarlo ##########################");
        System.out.println(operaciones.getPilots().readPilot(1));

        operaciones.getPilots().deletePilot(p1);

        System.out.println("\nPilotos despues de eliminar a Lewis Hamilton ##########################");
        operaciones.getPilots().readPilots().forEach(p -> System.out.println(p.getName()));

        operaciones.ShowPilotClassificationPilots();

        operaciones.ShowPilotClassificationConstructors();
    }

}
