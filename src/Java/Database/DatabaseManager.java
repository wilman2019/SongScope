package Java.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import io.github.cdimascio.dotenv.Dotenv;

public class DatabaseManager {

    static final Dotenv dotenv = Dotenv.load();
    static final String username = dotenv.get("DB_USER");
    static final String password = dotenv.get("DB_PASSWORD");
    static final String databaseURL = dotenv.get("DB_URL");

    
    private static Connection connection;

    // Method to establish a database connection
    public static Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(databaseURL, username, password);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    // Method to close the database connection
    public static void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to query the database and return results
    public static ResultSet query(PreparedStatement query) {
        try {
            return query.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Method to insert/delete/update into the database
    public static void update(PreparedStatement stmt) {
        try {
            connection = getConnection();
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }







}
