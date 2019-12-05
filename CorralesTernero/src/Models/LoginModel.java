package Models;

import DataAccesor.SQLConnectionHelper;
import Utils.Token;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Carlos Contreras
 */
public class LoginModel {

    public LoginModel() {

    }

    public boolean login(Token token) {
        SQLConnectionHelper.setToken(token);
        Statement conexion = SQLConnectionHelper.getConnection();
        if (conexion == null) {
            return false;
        }
        try {
            // Test bd
            conexion.execute("SELECT (1)");
        } catch (SQLException e) {
            Token.expireToken();
            return false;
        }
        Token.refreshToken();
        return true;
    }

}
