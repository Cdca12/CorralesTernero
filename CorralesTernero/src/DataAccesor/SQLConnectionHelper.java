package DataAccesor;

import java.sql.*;

/**
 *
 * @author Carlos Contreras
 */
public class SQLConnectionHelper {

    static private String url = null;
    static private Statement statement = null;
    static private Connection conn;

    static synchronized public Statement getConnection() {
        if (statement == null) {
            url = "jdbc:sqlserver://127.0.0.1\\MSSQLSERVER01:8731;databaseName=CorralesTernero;user=desarrollo;password=desarrollo;";
            try {
                conn = DriverManager.getConnection(url);
                statement = conn.createStatement();
            } catch (SQLException e) {
                return null;
            }
        }
        return statement;
    }

    static synchronized public void closeConnection() {
        try {
            conn.close();
            statement.close();
        } catch (SQLException E) {
        }
    }
}
