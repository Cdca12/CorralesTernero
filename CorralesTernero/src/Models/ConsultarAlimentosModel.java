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
public class ConsultarAlimentosModel {

    public ConsultarAlimentosModel() {
        
    }
    
    public Vector<Vector<String>> obtenerDatosTabla(Tipo type) {
        Vector<Vector<String>> datosTablaAlimentos = new Vector<>();

        Statement conexion = SQLConnectionHelper.getConnection();
        if (conexion == null) {
            return null;
        }
        try {
            String SQL = "SELECT * FROM Alimentos";
            if (type == Tipo.SELECCION) {
                SQL += " WHERE Nombre NOT LIKE '%con medicinas'";
            }
            ResultSet resultQuery = conexion.executeQuery(SQL);
            Vector<String> row;
            while (resultQuery.next()) {
                row = new Vector();
                row.add(resultQuery.getString(1));
                row.add(resultQuery.getString(2));
                row.add(resultQuery.getString(3));
                datosTablaAlimentos.add(row);
            }
        } catch (SQLException e) {
            return null;
        }
        return datosTablaAlimentos;
    }
}
