package Models;

import DataAccesor.SQLConnectionHelper;
import Utils.Tipo;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

/**
 *
 * @author Carlos Contreras
 */
public class ConsultarDietasModel {

    public ConsultarDietasModel() {

    }

    public Vector<Vector<String>> obtenerDatosTabla(Tipo type) {
        Vector<Vector<String>> datosTablaDietas = new Vector<>();

        Statement conexion = SQLConnectionHelper.getStatement();
        if (conexion == null) {
            return null;
        }
        try {
            String subConsulta = "Alimentos";
            if (type == Tipo.SELECCION) {
                // Obtener alimentos sin medicima
                subConsulta = "(SELECT * FROM Alimentos WHERE Nombre NOT LIKE '%con medicinas')"; 
            }
            ResultSet resultQuery = conexion.executeQuery(
                    "SELECT D.DietaID, A.Nombre, A.Cantidad, D.DiasTratamiento FROM Dietas D "
                    + "INNER JOIN " + subConsulta + " A "
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
