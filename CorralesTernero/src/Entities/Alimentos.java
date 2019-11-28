package Entities;

import DataAccesor.SQLConnectionHelper;
import Utils.OtroStatus;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Carlos Contreras
 */
public class Alimentos {
    
    private String AlimentoID;
    private String Nombre;
    private String Cantidad;
    
    public Alimentos() {
    }

    public String getAlimentoID() {
        return AlimentoID;
    }

    public void setAlimentoID(String AlimentoID) {
        this.AlimentoID = AlimentoID;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getCantidad() {
        return Cantidad;
    }

    public void setCantidad(String Cantidad) {
        this.Cantidad = Cantidad;
    }
    
    // Métodos CRUD
    public static synchronized int añadirAlimento(Alimentos alimento) {
        Statement conexion = SQLConnectionHelper.getConnection();
        if (conexion == null) {
            return OtroStatus.ERROR_CONNECTION.CODE;
        }
        try {
            conexion.execute(
                    "INSERT INTO Alimentos (Nombre, Cantidad) "
                    + "VALUES ("
                    + "'" + alimento.Nombre + "'" + ", "
                    + alimento.Cantidad + ");");
        } catch (SQLException e) {
            return OtroStatus.ERROR_INSERT.CODE;
        }
        return OtroStatus.OK_INSERT.CODE;
    }
    
}
