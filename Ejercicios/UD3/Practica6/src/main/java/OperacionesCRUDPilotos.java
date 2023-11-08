import Entities.Pilot;

import java.nio.file.Path;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OperacionesCRUDPilotos {

    private static ArrayList<Pilot> pilots;
    private static final Path PATH = Path.of("src", "main", "resources", "f12006sqlite.db");

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final String DRIVERS_TABLE_NAME = "drivers";
    private static final String RESULTS_TABLE_NAME = "results";
    private static final String COLUMN_1 = "driverid";
    private static final String COLUMN_2 = "code";
    private static final String COLUMN_3 = "forename";
    private static final String COLUMN_4 = "surname";
    private static final String COLUMN_5 = "dob";
    private static final String COLUMN_6 = "nationality";
    private static final String PREPARE_LIST_QUERY = "SELECT driverid, code, forename, surname, strftime('%d/%m/%Y') AS dob, nationality FROM drivers";

    public OperacionesCRUDPilotos(){
        prepareList();
    }

    public void prepareList(){
        pilots = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:" + PATH)){
            PreparedStatement statement = conn.prepareStatement(PREPARE_LIST_QUERY);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                pilots.add(new Pilot(
                        rs.getInt(COLUMN_1),
                        rs.getString(COLUMN_2),
                        rs.getString(COLUMN_3),
                        rs.getString(COLUMN_4),
                        DATE_FORMATTER.parse(rs.getString(COLUMN_5), LocalDate::from),
                        rs.getString(COLUMN_6)
                ));
            }
        }catch (SQLException e){
            System.err.println("ERROR: Couldn't get data from database");
        }
    }

    public void CreatePilot(Pilot pilot){
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:" + PATH)){
            String insertStatement = "INSERT INTO " + DRIVERS_TABLE_NAME + "(driverid, code, forename, surname, dob, nationality) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement insert = conn.prepareStatement(insertStatement);
            insert.setInt(1, pilot.getId());
            insert.setString(2, pilot.getCode());
            insert.setString(3, pilot.getName());
            insert.setString(4, pilot.getSurname());
            insert.setString(5, pilot.getDob().toString());
            insert.setString(6, pilot.getNationality());

            insert.executeUpdate();
        } catch (SQLException e) {
            System.err.println("ERROR: Couldn't add pilot to database");
        }
        pilots.add(pilot);
    }

    public Pilot ReadPilot(int id){
        return pilots.stream().filter(pilot -> pilot.getId() == id).findFirst().orElse(null);
    }

    public List<Pilot> ReadPilots(){
        return pilots;
    }

    public void UpdatePilot(Pilot pilot){
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:" + PATH)){
            String updateStatement = "UPDATE " + DRIVERS_TABLE_NAME + " SET " +
                    COLUMN_2 + " = ?, " +
                    COLUMN_3 + " = ?, " +
                    COLUMN_4 + " = ?, " +
                    COLUMN_5 + " = ?, " +
                    COLUMN_6 + " = ? " +
                    "WHERE " + COLUMN_1 + " = ?";
            PreparedStatement update = conn.prepareStatement(updateStatement);
            update.setString(1, pilot.getCode());
            update.setString(2, pilot.getName());
            update.setString(3, pilot.getSurname());
            update.setString(4, pilot.getDob().toString());
            update.setString(5, pilot.getNationality());
            update.setString(6, pilot.getNationality());
        } catch (SQLException e) {
            System.err.println("ERROR: Couldn't update pilot in database");
        }
        pilots.set(pilots.indexOf(ReadPilot(pilot.getId())), pilot);
    }

    public void DeletePilot(Pilot pilot){
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:" + PATH)){
            String deleteStatement = "DELETE FROM " + DRIVERS_TABLE_NAME + " WHERE " + COLUMN_1 + " = " + pilot.getId();
            PreparedStatement delete = conn.prepareStatement(deleteStatement);
            delete.executeUpdate();
        } catch (SQLException e) {
            System.err.println("ERROR: Couldn't delete pilot from database");
        }
        pilots.remove(ReadPilot(pilot.getId()));
    }

    public void ShowPilotClassification(){
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:" + PATH)){
            String queryStatement = "SELECT d.forename, d.surname, r.position " +
                    "FROM ? d JOIN ? r ON r.driverid = d.driverid " +
                    "WHERE r.position IS NOT NULL and r.raceid = (SELECT MAX(raceid) FROM results) " +
                    "ORDER BY r.position;";

            PreparedStatement query = conn.prepareStatement(queryStatement);
            query.setString(1, DRIVERS_TABLE_NAME);
            query.setString(2, RESULTS_TABLE_NAME);
            ResultSet rs = query.executeQuery();
            List<String> classification = new ArrayList<>();

            while(rs.next()){
                classification.add(rs.getInt("position") + " - " + rs.getString("forename") + " " + rs.getString("surname"));
            }

            System.out.println("Resultados de la última carrera:");
            classification.forEach(System.out::println);
        } catch (SQLException e) {
            System.err.println("ERROR: Couldn't show pilot classification");
        }
    }

    public void ShowPilotClassificationByPoints(){

        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:" + PATH)){
            String queryStatement = "SELECT d.forename, d.surname, r.position, r.points " +
                    "FROM drivers d JOIN results r ON r.driverid = d.driverid " +
                    "WHERE r.position IS NOT NULL and r.raceid = (SELECT MAX(raceid) FROM results) " +
                    "ORDER BY r.points DESC, r.position ASC;";

            PreparedStatement query = conn.prepareStatement(queryStatement);
            ResultSet rs = query.executeQuery();
            List<String> classification = new ArrayList<>();

            while(rs.next()){
                classification.add(
                        "(" + rs.getInt("points") + ") " +
                                rs.getString("position") + " - " +
                                rs.getString("forename") + " " +
                                rs.getString("surname"));
            }

            System.out.println("Resultados de la última carrera:");
            classification.forEach(System.out::println);
        } catch (SQLException e) {
            System.err.println("ERROR: Couldn't show pilot classification");
        }
    }

}
