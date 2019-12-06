package Models;

import DataAccesor.SQLConnectionHelper;
import Utils.Status;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Carlos Contreras
 */
public class AvanzarDiasModel {

    public AvanzarDiasModel() {

    }

    public synchronized Status avanzarDias(int numeroDias) {
        Statement conexion = SQLConnectionHelper.getConnection();
        if (conexion == null) {
            return Status.ERROR_CONNECTION;
        }
        try {
            conexion.execute("EXECUTE spAvanzarDias " + numeroDias);
        } catch (SQLException e) {
            return Status.ERROR_AVANZAR_DIAS;
        }
        return Status.OK_AVANZAR_DIAS;
    }

}
