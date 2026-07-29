package V1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnections {
    private static final String URL = "jdbc:postgresql://localhost:5432/caveX";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "pgyjavac";

    public Connection getConnection() throws SQLException {
        Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        System.out.println("Connection built");
        return connection;
    }
    public void closeConnection(Connection connection) throws SQLException {
        connection.close();
        System.out.println("Connection closed");
    }
}
