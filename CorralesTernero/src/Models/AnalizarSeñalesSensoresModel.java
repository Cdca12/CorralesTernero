package Models;

import DataAccesor.SQLConnectionHelper;
import Utils.Status;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

/**
 *
 * @author Carlos Contreras
 */
public class AnalizarSeñalesSensoresModel {

    public ArrayList<String> sensoresID;

    public AnalizarSeñalesSensoresModel() {
        sensoresID = new ArrayList();
    }

    public Vector<Vector<String>> obtenerDatosTabla() {
        Vector<Vector<String>> datosTablaSeñalesSensores = new Vector<>();

        Statement conexion = SQLConnectionHelper.getStatement();
        if (conexion == null) {
            return null;
        }
        try {
            ResultSet resultQuery = conexion.executeQuery(
                    "SELECT * FROM SeñalesSensores");
            Vector<String> row;
            while (resultQuery.next()) {
                row = new Vector();
                row.add(resultQuery.getString(1));
                row.add(resultQuery.getString(2));
                row.add(resultQuery.getString(3));
                row.add(resultQuery.getString(4));
                row.add(resultQuery.getString(5));
                row.add(resultQuery.getString(6));
                datosTablaSeñalesSensores.add(row);
            }
        } catch (SQLException e) {
            return null;
        }
        return datosTablaSeñalesSensores;
    }

    public Vector<Vector<String>> obtenerDatosTablaPropensosEnfermarse() {
        Vector<Vector<String>> datosTablaPropensosEnfermarse = new Vector<>();

        Statement conexion = SQLConnectionHelper.getStatement();
        if (conexion == null) {
            return null;
        }
        try {
            ResultSet resultQuery = conexion.executeQuery(
                    "SELECT * FROM ReporteCriasPropensaEnfermarseView");
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
                datosTablaPropensosEnfermarse.add(row);
            }
        } catch (SQLException e) {
            return null;
        }
        return datosTablaPropensosEnfermarse;
    }

    public synchronized Status añadirCuarentena(String criaID) {
        Statement conexion = SQLConnectionHelper.getStatement();
        if (conexion == null) {
            return Status.ERROR_CONNECTION;
        }
        try {
            conexion.execute("EXECUTE spAñadirCuarentena " + criaID);
        } catch (SQLException e) {
            return Status.ERROR_ADD_CUARENTENA;
        }
        return Status.OK_ADD_CUARENTENA;
    }

    public synchronized Status añadirCuarentenaAll() {
        Statement conexion = SQLConnectionHelper.getStatement();
        if (conexion == null) {
            return Status.ERROR_CONNECTION;
        }
        try {
            conexion.execute("EXECUTE spAñadirCuarentenaAll");
        } catch (SQLException e) {
            return Status.ERROR_ADD_CUARENTENA_ALL;
        }
        return Status.OK_ADD_CUARENTENA_ALL;
    }

}
