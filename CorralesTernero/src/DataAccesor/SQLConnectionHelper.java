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
            // Base de Datos local
             url = "jdbc:sqlserver://localhost\\MSSQLSERVER01:1433;databaseName=CorralesTernero;user=desarrollo;password=desarrollo;";

            // Base de Datos en Azure
            // url = "jdbc:sqlserver://pruebascdca.database.windows.net:1433;database=CorralesTernero;user=Cdca;password=Corrales$123";

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
