import java.sql.*;

public class PostgreSQLManager {

    public static final String USER = "postgres";
    public static final String PASSWORD = "qwerty1234";
    public static final String ENDPOINT = "examenad.ci66saah1axn.us-east-1.rds.amazonaws.com";
    public static final int PORT = 5432;
    public static final String DATABASE = "nebflo";
    private static final String URL = String.format("jdbc:postgresql://%s:%d/%s", ENDPOINT, PORT, DATABASE);
    private Connection connection;

    private void startConnection(){
        try{
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        }catch (SQLException e){
            System.out.println("Error connecting to the database");
        }
    }
    private void stopConnection(){
        try{
            connection.close();
        }catch (SQLException e){
            System.out.println("Error closing the connection");
        }
    }

    public void createAccount(Account acc){
        startConnection();
        try{
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM accounts WHERE iban = ?");
            statement.setString(1, acc.getIban());
            ResultSet resultSet = statement.executeQuery();
            if(!resultSet.next()){
                statement = connection.prepareStatement(
                        "INSERT INTO accounts (iban, balance, clientid) VALUES (?, ?, ?)");
                statement.setString(1, acc.getIban());
                statement.setDouble(2, acc.getBalance());
                statement.setInt(3, acc.getClientid());
                statement.executeUpdate();
            }else{
                System.err.println("Account with same IBAN already exists");
            }
        }catch (SQLException e){
            System.out.println("Error creating account");
        }
        stopConnection();
    }

    public void deleteAccount(Account acc){
        startConnection();
        try{
            PreparedStatement statement = connection.prepareStatement(
                    "DELETE FROM accounts WHERE iban = ?");
            statement.setString(1, acc.getIban());
            statement.executeUpdate();
        }catch (SQLException e){
            System.out.println("Error deleting account");
        }
        stopConnection();
    }

    public void deleteAccountById(int id){
        startConnection();
        try{
            PreparedStatement statement = connection.prepareStatement(
                    "DELETE FROM accounts WHERE accountid = ?");
            statement.setInt(1, id);
            statement.executeUpdate();
        }catch (SQLException e){
            System.out.println("Error deleting account");
        }
        stopConnection();
    }

    public void deleteAllAccountsFromClientId(int id){
        startConnection();
        try{
            PreparedStatement statement = connection.prepareStatement(
                    "DELETE FROM accounts WHERE clientid = ?");
            statement.setInt(1, id);
            statement.executeUpdate();
        }catch (SQLException e){
            System.out.println("Error deleting account");
        }
        stopConnection();
    }

    public void updateAccount(Account acc){
        startConnection();
        try{
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE accounts SET iban = ?, balance = ?, clientid = ? WHERE accountid = ?");
            statement.setString(1, acc.getIban());
            statement.setDouble(2, acc.getBalance());
            statement.setInt(3, acc.getClientid());
            statement.setInt(4, acc.getId());
            statement.executeUpdate();
        }catch (SQLException e){
            System.out.println("Error updating account");
        }
        stopConnection();
    }

    public void showAllAccounts(){
        startConnection();
        try{
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM accounts");
            ResultSet resultSet = statement.executeQuery();
            Account temp;
            while (resultSet.next()){
                temp = new Account(resultSet.getString("iban"),
                        resultSet.getDouble("balance"),
                        resultSet.getInt("clientid"));
                temp.setId(resultSet.getInt("accountid"));
                System.out.println(temp);
            }
        }catch (SQLException e){
            System.out.println("Error searching for accounts");
        }
    }

    public void showAccountById(int id){
        startConnection();
        try{
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM accounts WHERE accountid = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            Account temp;
            while (resultSet.next()){
                temp = new Account(resultSet.getString("iban"),
                        resultSet.getDouble("balance"),
                        resultSet.getInt("clientid"));
                temp.setId(resultSet.getInt("accountid"));
                System.out.println(temp);
            }
        }catch (SQLException e){
            System.out.println("Error searching for accounts");
        }
    }

    public void makeTransaction(int origin, int destination, double amount){
        startConnection();
        try{
            connection.setAutoCommit(false);
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM accounts WHERE accountid = ?");
            statement.setInt(1, origin);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            Account originAccount = new Account(resultSet.getString("iban"),
                    resultSet.getDouble("balance"),
                    resultSet.getInt("clientid"));
            statement.setInt(1, destination);
            resultSet = statement.executeQuery();
            resultSet.next();
            Account destinationAccount = new Account(resultSet.getString("iban"),
                    resultSet.getDouble("balance"),
                    resultSet.getInt("clientid"));
            if(originAccount.getBalance() < amount){
                System.err.println("Not enough money");
            }else{
                statement = connection.prepareStatement("UPDATE accounts SET balance = ? WHERE accountid = ?");
                statement.setDouble(1, originAccount.getBalance() - amount);
                statement.setInt(2, origin);
                statement.executeUpdate();

                statement.setDouble(1, destinationAccount.getBalance() + amount);
                statement.setInt(2, destination);
                statement.executeUpdate();
                connection.commit();
            }
        }catch (SQLException e){
            System.out.println("Error making transaction");
        }catch (Exception e){
            System.err.println(e.getMessage());
        }finally {
            try{
                connection.setAutoCommit(true);
            }catch (SQLException e2){
                System.out.println("Error setting autocommit to true");
            }
            stopConnection();
        }

    }

    public Account getAccountById(int id){
        startConnection();
        try{
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM accounts WHERE accountid = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            Account temp;
            while (resultSet.next()){
                temp = new Account(resultSet.getString("iban"),
                        resultSet.getDouble("balance"),
                        resultSet.getInt("clientid"));
                temp.setId(resultSet.getInt("accountid"));
                stopConnection();
                return temp;
            }
        }catch (SQLException e){
            System.out.println("Error searching for accounts");
        }
        stopConnection();
        return null;
    }

}
