package Models;

import DataAccesor.SQLConnectionHelper;
import Utils.Config;
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
    
    public Vector<Vector<String>> obtenerDatosTabla(Config config) {
        Vector<Vector<String>> datosTablaCorrales = new Vector<>();
        Statement conexion = SQLConnectionHelper.getConnection();
        if (conexion == null) {
            return null;
        }
        try {
            String subConsulta = "TipoCorral";
            if (config == Config.SELECCION) {
                subConsulta = "(SELECT * FROM TipoCorral WHERE TipoCorralID = 1)"; // Obtener Corrales Sanos solamente
            }
            String SQL = "SELECT C.CorralID, E.Nombre as Estado, T.Descripcion as Tipo FROM Corrales C "
                            + "INNER JOIN Estados E ON C.EstadoID = E.EstadoID " 
                            + "INNER JOIN " + subConsulta + " T ON C.TipoCorralID = T.TipoCorralID";
            ResultSet resultQuery = conexion.executeQuery(SQL);
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
