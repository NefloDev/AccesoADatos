import Entities.Constructors;
import Entities.Pilots;

import java.nio.file.Path;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OperacionesCRUD {
    private static Constructors constructors;
    private static Pilots pilots;
    private static final Path PATH = Path.of("src", "main", "resources", "f12006sqlite.db");
    private static final String PREPARE_CONSTRUCTORS_QUERY = "SELECT constructorid, name, nationality FROM constructors";
    private static final String PREPARE_LIST_QUERY = "SELECT driverid, code, forename, surname, strftime('%d/%m/%Y') AS dob, nationality, constructorid FROM drivers";

    public OperacionesCRUD(){
        constructors = new Constructors(PATH, PREPARE_CONSTRUCTORS_QUERY);
        pilots = new Pilots(constructors, PATH, PREPARE_LIST_QUERY);
    }

    public Pilots getPilots(){
        return pilots;
    }

    public Constructors getConstructors(){
        return constructors;
    }

    public void ShowPilotClassificationPilots(){
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:" + PATH)){
            String queryStatement = "SELECT d.forename || ' ' || d.surname as Nombre, SUM(r.points) as Puntos " +
                    "FROM drivers d JOIN results r ON r.driverid = d.driverid " +
                    "WHERE r.position IS NOT NULL " +
                    "GROUP BY d.driverid " +
                    "ORDER BY Puntos DESC;";

            PreparedStatement query = conn.prepareStatement(queryStatement);
            ResultSet rs = query.executeQuery();
            List<String> classification = new ArrayList<>();
            int pos = 1;
            System.out.println();

            while(rs.next()){
                classification.add(pos++ + " - " + rs.getString("Nombre") + " - " + rs.getInt("Puntos"));
            }

            System.out.println("Resultados de la última carrera:");
            classification.forEach(System.out::println);
        } catch (SQLException e) {
            System.err.println("ERROR: Couldn't show pilot classification");
        }
    }

    public void ShowPilotClassificationConstructors(){

        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:" + PATH)){
            String queryStatement = "SELECT d.constructorid, c.name, SUM(r.points) as Points " +
                    "FROM results r JOIN drivers d ON r.driverid = d.driverid " +
                    "JOIN constructors c ON d.constructorid = c.constructorid " +
                    "GROUP BY d.constructorid, c.name " +
                    "ORDER BY Points DESC;";

            PreparedStatement query = conn.prepareStatement(queryStatement);
            ResultSet rs = query.executeQuery();
            List<String> classification = new ArrayList<>();
            int pos = 1;
            System.out.println();

            while(rs.next()){
                classification.add(pos++ + " - " + rs.getString("name") + " - " + rs.getInt("Points"));
            }

            System.out.println("Resultados de la última carrera:");
            classification.forEach(System.out::println);
        } catch (SQLException e) {
            System.err.println("ERROR: Couldn't show pilot classification");
        }
    }

}
