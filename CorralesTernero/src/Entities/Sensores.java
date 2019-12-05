package Entities;

import DataAccesor.SQLConnectionHelper;
import Utils.Status;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Carlos Contreras
 */
public class Sensores {

    private String SensorID;
    private String Marca;
    private String CriaID;

    public Sensores() {
    }

    public String getSensorID() {
        return SensorID;
    }

    public void setSensorID(String SensorID) {
        this.SensorID = SensorID;
    }

    public String getMarca() {
        return Marca;
    }

    public void setMarca(String Marca) {
        this.Marca = Marca;
    }

    public String getCriaID() {
        return CriaID;
    }

    public void setCriaID(String CriaID) {
        this.CriaID = CriaID;
    }

    // Métodos CRUD
    public static synchronized int añadirSensor(Sensores sensor) {
        Statement conexion = SQLConnectionHelper.getConnection();
        if (conexion == null) {
            return Status.ERROR_CONNECTION.CODE;
        }
        try {
            String SQL = "INSERT INTO Sensores "
                    + "VALUES ("
                    + "'" + sensor.Marca + "')";
            conexion.execute(SQL);
        } catch (SQLException e) {
            return Status.ERROR_INSERT.CODE;
        }
        return Status.OK_INSERT.CODE;
    }
    
    public static synchronized int añadirSensor(Sensores sensor, int cantidad) {
        Statement conexion = SQLConnectionHelper.getConnection();
        if (conexion == null) {
            return Status.ERROR_CONNECTION.CODE;
        }
        try {
            String SQL = "INSERT INTO Sensores "
                    + "VALUES ("
                    + "'" + sensor.Marca + "')";
            for (int i = 0; i < cantidad; conexion.execute(SQL), i++); // iterar cantidad
        } catch (SQLException e) {
            return Status.ERROR_INSERT.CODE;
        }
        return Status.OK_INSERT.CODE;
    }

}
