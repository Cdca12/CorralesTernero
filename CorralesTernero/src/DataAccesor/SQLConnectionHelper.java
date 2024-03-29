package DataAccesor;

import Utils.Token;
import java.sql.*;

/**
 *
 * @author Carlos Contreras
 */
public class SQLConnectionHelper {

    static private String url = null;
    static private Statement statement = null;
    static private Connection conn;
    static private Token token;

    static synchronized public void setToken(Token tkn) {
        token = tkn;
    }

    public static synchronized Token getToken() {
        return token;
    }

    static synchronized public Statement getStatement() {
        if (statement == null) {
            // Base de Datos en Azure
            url = "jdbc:sqlserver://pruebascdca.database.windows.net:1433;database=CorralesTernero;"
                    + "user=" + token.getUser()
                    + ";password=" + token.getPass()
                    + ";encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
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
