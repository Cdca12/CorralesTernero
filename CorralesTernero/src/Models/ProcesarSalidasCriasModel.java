package Models;

import DataAccesor.SQLConnectionHelper;
import Utils.Status;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

/**
 *
 * @author Carlos Contreras
 */
public class ProcesarSalidasCriasModel {

    public ProcesarSalidasCriasModel() {

    }

    public Vector<Vector<String>> obtenerDatosTabla() {
        Vector<Vector<String>> datosTabla = new Vector<>();

        Statement conexion = SQLConnectionHelper.getConnection();
        if (conexion == null) {
            return null;
        }
        try {
            ResultSet resultQuery = conexion.executeQuery(
                    "SELECT * FROM ReporteCriasProcesarSalidaView");
            Vector<String> row;
            while (resultQuery.next()) {
                row = new Vector();
                row.add(resultQuery.getString(1));
                row.add(resultQuery.getString(2));
                row.add(resultQuery.getString(3));
                row.add(resultQuery.getString(4));
                row.add(resultQuery.getString(5));
                row.add(resultQuery.getString(6));
                row.add(resultQuery.getString(7));
                row.add(resultQuery.getString(8));
                datosTabla.add(row);
            }
        } catch (SQLException e) {
            return null;
        }
        return datosTabla;
    }

    public synchronized Status procesarSalidas(String criaID) {
        Statement conexion = SQLConnectionHelper.getConnection();
        if (conexion == null) {
            return Status.ERROR_CONNECTION;
        }
        try {
            conexion.execute("EXECUTE spProcesarSalidasCrias " + criaID);
        } catch (SQLException e) {
            return Status.ERROR_PROCCESS;
        }
        return Status.OK_PROCCESS;
    }

    public synchronized Status procesarSalidasAll() {
        Statement conexion = SQLConnectionHelper.getConnection();
        if (conexion == null) {
            return Status.ERROR_CONNECTION;
        }
        try {
            conexion.execute("EXECUTE spProcesarSalidasCriasAll");
        } catch (SQLException e) {
            return Status.ERROR_PROCCESS_ALL;
        }
        return Status.OK_PROCCESS_ALL;
    }

}
