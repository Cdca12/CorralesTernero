package Entities;

import DataAccesor.SQLConnectionHelper;
import Utils.Status;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Carlos Contreras
 */
public class Corrales {
    
    private String CorralID;
    private String EstadoID;
    private String TipoCorralID;
    
    public Corrales() {
    }

    public String getCorralID() {
        return CorralID;
    }

    public void setCorralID(String CorralID) {
        this.CorralID = CorralID;
    }

    public String getEstadoID() {
        return EstadoID;
    }

    public void setEstadoID(String EstadoID) {
        this.EstadoID = EstadoID;
    }

    public String getTipoCorralID() {
        return TipoCorralID;
    }

    public void setTipoCorralID(String TipoCorralID) {
        this.TipoCorralID = TipoCorralID;
    }
    
    @Override
    public String toString() {
        return CorralID;
    }
    
    
    // Métodos CRUD
    public static synchronized Status añadirCorral(Corrales corral) {
        Statement conexion = SQLConnectionHelper.getStatement();
        if (conexion == null) {
            return Status.ERROR_CONNECTION;
        }
        try {
            conexion.execute(
                    "INSERT INTO Corrales (EstadoID, TipoCorralID) "
                    + "VALUES ("
                    + "'" + corral.EstadoID + "'" + ", "
                    + corral.TipoCorralID + ");");
        } catch (SQLException e) {
            return Status.ERROR_INSERT_CORRAL;
        }
        return Status.OK_INSERT_CORRAL;
    }

    
}
