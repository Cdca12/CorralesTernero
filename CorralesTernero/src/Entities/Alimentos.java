package Entities;

import DataAccesor.SQLConnectionHelper;
import Utils.Status;
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
    public static synchronized Status añadirAlimento(Alimentos alimento) {
        Statement conexion = SQLConnectionHelper.getConnection();
        if (conexion == null) {
            return Status.ERROR_CONNECTION;
        }
        try {
            conexion.execute(
                    "INSERT INTO Alimentos (Nombre, Cantidad) "
                    + "VALUES ("
                    + "'" + alimento.Nombre + "'" + ", "
                    + alimento.Cantidad + ");");
        } catch (SQLException e) {
            return Status.ERROR_INSERT_ALIMENTO;
        }
        return Status.OK_INSERT_ALIMENTO;
    }
    
}
