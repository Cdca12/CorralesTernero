package Models;

import DataAccesor.SQLConnectionHelper;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Carlos Contreras
 */
public class AvanzarDiasModel {

    public AvanzarDiasModel() {

    }

    public void avanzarDias(int numeroDias) {
        Statement conexion = SQLConnectionHelper.getConnection();
        if (conexion == null) {
            return;
        }
        try {
            conexion.execute("EXECUTE spAvanzarDias " + numeroDias);
        } catch (SQLException e) {
            System.out.println("Error");
            return;
        }
    }

}
