package Entities;

import java.nio.file.Path;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static Entities.Columns.PILOT;

public class Pilots {

    private List<Pilot> pilots;
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final String PILOTS_TABLE_NAME = "drivers";
    private String pilotsQuery;
    private Path path;
    private Constructors constructors;

    public Pilots(Constructors constructors, Path path, String pilotsQuery){
        this.path = path;
        this.pilotsQuery = pilotsQuery;
        this.constructors = constructors;
        prepareList();
    }

    public Pilot readPilot(int id){
        return pilots.stream().filter(pilot -> pilot.getId() == id).findFirst().orElse(null);
    }

    public List<Pilot> readPilots(){
        return pilots;
    }

    private void prepareList(){
        pilots = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:" + this.path)){
            PreparedStatement statement = conn.prepareStatement(pilotsQuery);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                pilots.add(new Pilot(
                        rs.getInt(PILOT.get(0)),
                        rs.getString(PILOT.get(1)),
                        rs.getString(PILOT.get(2)),
                        rs.getString(PILOT.get(3)),
                        DATE_FORMATTER.parse(rs.getString(PILOT.get(4)), LocalDate::from),
                        rs.getString(PILOT.get(5)),
                        constructors.getConstructorById(rs.getInt(PILOT.get(6)))
                ));
            }
        }catch (SQLException e){
            System.err.println("ERROR: Couldn't get data from database");
        }
    }

    public void createPilot(Pilot pilot){
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:" + path)){
            String insertStatement = "INSERT INTO " + PILOTS_TABLE_NAME + "(" +
                    PILOT.get(0) + ", " +
                    PILOT.get(1) + ", " +
                    PILOT.get(2) + ", " +
                    PILOT.get(3) + ", " +
                    PILOT.get(4) + ", " +
                    PILOT.get(5) + ", " +
                    PILOT.get(6) +
                    ") VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement insert = conn.prepareStatement(insertStatement);
            insert.setInt(1, pilot.getId());
            insert.setString(2, pilot.getCode());
            insert.setString(3, pilot.getName());
            insert.setString(4, pilot.getSurname());
            insert.setString(5, pilot.getDob().toString());
            insert.setString(6, pilot.getNationality());
            insert.setInt(7, pilot.getConstructor().getId());

            insert.executeUpdate();
        } catch (SQLException e) {
            System.err.println("ERROR: Couldn't add pilot to database");
        }
        pilots.add(pilot);
    }

    public void updatePilot(Pilot pilot){
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:" + path)){
            String updateStatement = "UPDATE " + PILOTS_TABLE_NAME + " SET " +
                    PILOT.get(1) + " = ?, " +
                    PILOT.get(2) + " = ?, " +
                    PILOT.get(3) + " = ?, " +
                    PILOT.get(4) + " = ?, " +
                    PILOT.get(5) + " = ?, " +
                    PILOT.get(6) + " = ? " +
                    "WHERE " + PILOT.get(0) + " = ?";
            PreparedStatement update = conn.prepareStatement(updateStatement);
            update.setString(1, pilot.getCode());
            update.setString(2, pilot.getName());
            update.setString(3, pilot.getSurname());
            update.setString(4, pilot.getDob().toString());
            update.setString(5, pilot.getNationality());
            update.setInt(6, pilot.getConstructor().getId());
            update.setInt(7, pilot.getId());
        } catch (SQLException e) {
            System.err.println("ERROR: Couldn't update pilot in database");
        }
        pilots.set(pilots.indexOf(readPilot(pilot.getId())), pilot);
    }

    public void deletePilot(Pilot pilot){
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:" + path)){
            String deleteStatement = "DELETE FROM " + PILOTS_TABLE_NAME + " WHERE " + PILOT.get(0) + " = " + pilot.getId();
            PreparedStatement delete = conn.prepareStatement(deleteStatement);
            delete.executeUpdate();
        } catch (SQLException e) {
            System.err.println("ERROR: Couldn't delete pilot from database");
        }
        pilots.remove(readPilot(pilot.getId()));
    }


}
