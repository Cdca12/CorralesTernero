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
public class DietasModel {

    public DietasModel() {

    }

    public Vector<Vector<String>> obtenerDatosTabla() {
        Vector<Vector<String>> datosTablaDietas = new Vector<>();

        Statement conexion = SQLConnectionHelper.getConnection();
        if (conexion == null) {
            return null;
        }
        try {
            ResultSet resultQuery = conexion.executeQuery(
                    "SELECT D.DietaID, A.Nombre, A.Cantidad, D.DiasTratamiento FROM Dietas D "
                    + "INNER JOIN Alimentos A "
                    + "ON D.AlimentoID = A.AlimentoID;");
            Vector<String> row;
            while (resultQuery.next()) {
                row = new Vector();
                row.add(resultQuery.getString(1));
                row.add(resultQuery.getString(2));
                row.add(resultQuery.getString(3));
                row.add(resultQuery.getString(4));
                datosTablaDietas.add(row);
            }
        } catch (SQLException e) {
            return null;
        }
        return datosTablaDietas;
    }

}
