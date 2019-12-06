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
public class ConsultarCriasASacrificarModel {

    public ConsultarCriasASacrificarModel() {

    }

    public Vector<Vector<String>> obtenerDatosTabla() {
        Vector<Vector<String>> datosTablaCriasASacrificar = new Vector<>();

        Statement conexion = SQLConnectionHelper.getConnection();
        if (conexion == null) {
            return null;
        }
        try {
            ResultSet resultQuery = conexion.executeQuery(
                    "SELECT * FROM ConsultarCriasASacrificarView"); // Solo me reduje a mandar a llamar a la vista
            Vector<String> row;
            while (resultQuery.next()) {
                row = new Vector();
                row.add(resultQuery.getString(1));
                row.add(resultQuery.getString(2));
                row.add(resultQuery.getString(3));
                row.add(resultQuery.getString(4));
                row.add(resultQuery.getString(5));
                row.add(resultQuery.getString(6));
                datosTablaCriasASacrificar.add(row);
            }
        } catch (SQLException e) {
            return null;
        }
        return datosTablaCriasASacrificar;
    }

    public synchronized Status sacrificarCria(String criaID) {
        Statement conexion = SQLConnectionHelper.getConnection();
        if (conexion == null) {
            return Status.ERROR_CONNECTION;
        }
        try {
            conexion.execute("EXECUTE spSacrificarCrias " + criaID);
        } catch (SQLException e) {
            return Status.ERROR_SACRIFICAR;
        }
        return Status.OK_SACRIFICAR;
    }

    public synchronized Status sacrificarCriaAll() {
        Statement conexion = SQLConnectionHelper.getConnection();
        if (conexion == null) {
            return Status.ERROR_CONNECTION;
        }
        try {
            conexion.execute("EXECUTE spSacrificarCriasAll");
        } catch (SQLException e) {
            return Status.ERROR_SACRIFICAR_ALL;
        }
        return Status.OK_SACRIFICAR_ALL;
    }

}
