package Models;

import DataAccesor.SQLConnectionHelper;
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
    
    public ArrayList<String> criasID;
    
     public AnalizarSeñalesSensoresModel() {
        
    }
    
    public Vector<Vector<String>> obtenerDatosTabla() {
        Vector<Vector<String>> datosTablaSeñalesSensores = new Vector<>();

        Statement conexion = SQLConnectionHelper.getConnection();
        if (conexion == null) {
            return null;
        }
        try {
            ResultSet resultQuery = conexion.executeQuery(
                    "SELECT * FROM SeñalesSensores"); 
            // TODO
            // Hacer un top x tuplas y con el buscador ya mostrar todas las señales, o preguntar cuantas señales y con un boton hacer la busqueda
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
}
