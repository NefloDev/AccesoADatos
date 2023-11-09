package Entities;

import java.nio.file.Path;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static Entities.Columns.CONSTRUCTOR;
import static Entities.Columns.PILOT;

public class Constructors {

    private List<Constructor> constructors;
    private static final String CONSTRUCTORS_TABLE_NAME = "constructors";
    private String constructorsQuery;
    private Path path;

    public Constructors(Path path, String constructorsQuery){
        this.path = path;
        this.constructorsQuery = constructorsQuery;
        prepareList();
    }

    private void prepareList(){
        constructors = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:" + path)){
            PreparedStatement statement = conn.prepareStatement(constructorsQuery);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                constructors.add(new Constructor(
                        rs.getInt(CONSTRUCTOR.get(0)),
                        rs.getString(CONSTRUCTOR.get(1)),
                        rs.getString(CONSTRUCTOR.get(2)))
                );
            }
        }catch (SQLException e){
            System.err.println("ERROR: Couldn't get data from database");
        }
    }

    public Constructor getConstructorById(int id){
        return constructors.stream().filter(constructor -> constructor.getId() == id).findFirst().orElse(null);
    }

    public void createConstructor(Constructor constructor){
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:" + path)){
            String insertStatement = "INSERT INTO " + CONSTRUCTORS_TABLE_NAME + "(" +
                    CONSTRUCTOR.get(0) + ", " +
                    CONSTRUCTOR.get(1) + ", " +
                    CONSTRUCTOR.get(2) +
                    ") VALUES (?, ?, ?)";
            PreparedStatement insert = conn.prepareStatement(insertStatement);
            insert.setInt(1, constructor.getId());
            insert.setString(2, constructor.getName());
            insert.setString(3, constructor.getNationality());

            insert.executeUpdate();
        } catch (SQLException e) {
            System.err.println("ERROR: Couldn't add constructor to database");
        }
        constructors.add(constructor);
    }

    public void updateConstructor(Constructor constructor){
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:" + path)){
            String updateStatement = "UPDATE " + CONSTRUCTORS_TABLE_NAME + " SET " +
                    CONSTRUCTOR.get(1) + " = ?, " +
                    CONSTRUCTOR.get(2) + " = ? " +
                    "WHERE " + PILOT.get(0) + " = ?";
            PreparedStatement update = conn.prepareStatement(updateStatement);
            update.setString(1, constructor.getName());
            update.setString(2, constructor.getNationality());
            update.setInt(3, constructor.getId());
        } catch (SQLException e) {
            System.err.println("ERROR: Couldn't update constructor in database");
        }
        constructors.set(constructors.indexOf(getConstructorById(constructor.getId())), constructor);
    }

    public void deleteConstructor(Constructor constructor){
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:" + path)){
            String deleteStatement = "DELETE FROM " + CONSTRUCTORS_TABLE_NAME + " WHERE " + CONSTRUCTOR.get(0) + " = " + constructor.getId();
            PreparedStatement delete = conn.prepareStatement(deleteStatement);
            delete.executeUpdate();
        } catch (SQLException e) {
            System.err.println("ERROR: Couldn't delete constructor from database");
        }
        constructors.remove(getConstructorById(constructor.getId()));
    }

}
