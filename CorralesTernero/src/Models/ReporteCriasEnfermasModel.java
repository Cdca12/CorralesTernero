package Models;

import DataAccesor.SQLConnectionHelper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

/**
 *
 * @author Carlos Contreras
 */
public class ReporteCriasEnfermasModel {
    
    public ReporteCriasEnfermasModel() {
        
    }
    
    public Vector<Vector<String>> obtenerDatosTabla() {
        Vector<Vector<String>> datosTablaCriasEnfermas = new Vector<>();

        Statement conexion = SQLConnectionHelper.getConnection();
        if (conexion == null) {
            return null;
        }
        try {
            ResultSet resultQuery = conexion.executeQuery(
                    "SELECT * FROM ReporteCriasEnfermasView"); // Solo me reduje a mandar a llamar a la vista
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
                row.add(resultQuery.getString(9));
                datosTablaCriasEnfermas.add(row);
            }
        } catch (SQLException e) {
            return null;
        }
        return datosTablaCriasEnfermas;
    }

}
