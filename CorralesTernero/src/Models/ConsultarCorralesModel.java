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
public class ConsultarCorralesModel {
    
    public ConsultarCorralesModel() {
        
    }
    
    public Vector<Vector<String>> obtenerDatosTabla() {
        Vector<Vector<String>> datosTablaCorrales = new Vector<>();

        Statement conexion = SQLConnectionHelper.getConnection();
        if (conexion == null) {
            return null;
        }
        try {
            ResultSet resultQuery = conexion.executeQuery(
                    "SELECT C.CorralID, E.Nombre as Estado, T.Descripcion as Tipo FROM Corrales C "
                            + "INNER JOIN Estados E ON C.EstadoID = E.EstadoID " 
                            + "INNER JOIN TipoCorral T ON C.TipoCorralID = T.TipoCorralID");
            Vector<String> row;
            while (resultQuery.next()) {
                row = new Vector();
                row.add(resultQuery.getString(1));
                row.add(resultQuery.getString(2));
                row.add(resultQuery.getString(3));
                datosTablaCorrales.add(row);
            }
        } catch (SQLException e) {
            return null;
        }
        return datosTablaCorrales;
    }
    
    
    
}
