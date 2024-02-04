import java.sql.*;
import Entities.Constructor;
import Entities.Pilot;

public class Main {
    public static void main(String[] args) {
        String endpoint = "sql106.infinityfree.com";
        String port = "5432";
        String usuario = "postgres";
        String passwd = "alejandro";
        String urlCon = "jdbc:postgresql://" + endpoint + ":" + port + "/f12006";

        Constructor seat = new Constructor("seat", "Seat F1", "Spanish");
        Pilot pilot1 = new Pilot("SAI", "Carlos", "Sainz", Date.valueOf("1994-09-01"), "Spanish", 0);
        Pilot pilot2 = new Pilot("ALM", "Manuel", "Alomán", Date.valueOf("2004-01-27"), "Spanish", 0);

        System.out.println("Conectando a la base de datos...");

        try (Connection conn = DriverManager.getConnection(urlCon, usuario, passwd)) {
            System.out.println("Conectado a la base de datos");
            try {
                conn.setAutoCommit(false);

                //Preparación del insert del constructor, piloto1 y piloto2
                PreparedStatement insertConstructor = insertConstructor(seat, conn);
                PreparedStatement insertPilot1 = insertPilot(pilot1, conn);
                PreparedStatement insertPilot2 = insertPilot(pilot2, conn);

                //Ejecuto el insert del constructor y recojo el id generado
                insertConstructor.executeUpdate();
                ResultSet res = insertConstructor.getGeneratedKeys();
                res.next();

                //Ejecuto los inserts de los pilotos con el id del constructor generado
                insertPilot1.setInt(6, res.getInt(1));
                insertPilot1.executeUpdate();
                insertPilot1.close();

                insertPilot2.setInt(6, res.getInt(1));
                insertPilot2.executeUpdate();
                insertPilot2.close();

                //Hasta que no termino de recoger los datos del ResultSet no cierro el Statemet del insert del constructor
                insertConstructor.close();

                //Preparo la llamada de un procedimiento almacenado que me devuelva los resultados de las carreras de un piloto
                CallableStatement resultsById = conn.prepareCall("{call get_results_by_driver(?)}");
                resultsById.setString(1, "ALO");
                resultsById.execute();
                ResultSet rs = resultsById.getResultSet();
                System.out.println("Results of driver ALO:");
                while (rs.next()) {
                    System.out.println(rs.getInt("round") + ".- " + rs.getString("circuit") + " | " + rs.getInt("result") + " | " + rs.getInt("points") + " | " + rs.getDate("date"));
                }

                //Preparo la llamada de un procedimiento almacenado que me devuelva la clasificación final de los pilotos por puntos
                CallableStatement finalClassification = conn.prepareCall("{call get_drivers_standings()}");
                finalClassification.execute();
                ResultSet rs2 = finalClassification.getResultSet();
                System.out.println("\nFinal classification:");
                while (rs2.next()) {
                    System.out.println(rs2.getString("driver") + " | " + rs2.getInt("points"));
                }

                conn.commit();
                conn.setAutoCommit(true);
            } catch (SQLException e1) {
                try {
                    conn.rollback();
                    System.err.println("Rollback realizado: " + e1.getMessage());
                } catch (SQLException e2) {
                    System.err.println("Error al hacer el rollback");
                }
            }
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    private static PreparedStatement insertPilot(Pilot p, Connection conn) throws SQLException {
        String insertPilot = "INSERT INTO drivers (code, forename, surname, dob, nationality, constructorid) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        PreparedStatement insert = conn.prepareStatement(insertPilot);
        insert.setString(1, p.getCode());
        insert.setString(2, p.getForename());
        insert.setString(3, p.getSurname());
        insert.setDate(4, p.getDob());
        insert.setString(5, p.getNationality());
        
        return insert;
    }

    private static PreparedStatement insertConstructor(Constructor c, Connection conn) throws SQLException {
        String insertConstructor = "INSERT INTO constructors (constructorref, name, nationality) " +
                "VALUES (?, ?, ?)";

        PreparedStatement insert = conn.prepareStatement(insertConstructor, PreparedStatement.RETURN_GENERATED_KEYS);
        insert.setString(1, c.getConstructorref());
        insert.setString(2, c.getName());
        insert.setString(3, c.getNationality());

        return insert;
    }

}
