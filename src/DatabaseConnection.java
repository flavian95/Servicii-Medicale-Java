import java.sql.*;

public class DatabaseConnection {
    private static DatabaseConnection instance;
    private Connection connection;
    private Statement statement;

    private DatabaseConnection() {
        try {
            connection = DriverManager.getConnection("#", "#", "#");
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        } catch (SQLException e) {
            System.out.println("Exceptie la conectare: " + e.getMessage());
        }
    }

    public Statement getStatement() {
        return statement;
    }

    public void close() {
        try {
            statement.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("Exceptie la inchidere: " + e.getMessage());
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public static DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }
}

