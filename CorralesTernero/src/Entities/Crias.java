package Entities;

import DataAccesor.SQLConnectionHelper;
import Utils.Status;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 * Clase que modela la entidad de Crias
 *
 * @author Carlos Contreras
 */
public class Crias {

    private String CriasID;
    private String CorralID;
    private String PesoID;
    private String GrasaCoberturaID;
    private String MusculoID;
    private String EstadoCriaID;
    private String DietaID;
    private String SensorID;
    private String VecesEnTratamiento;

    public Crias() {
        // Defaults
        this.EstadoCriaID = "DEFAULT"; // 1
        this.DietaID = "DEFAULT"; // 1
        this.SensorID = "NULL";
        this.VecesEnTratamiento = "DEFAULT"; // 0
    }

    public String getCriasID() {
        return CriasID;
    }

    public void setCriasID(String CriasID) {
        this.CriasID = CriasID;
    }

    public String getCorralID() {
        return CorralID;
    }

    public void setCorralID(String CorralID) {
        this.CorralID = CorralID;
    }

    public String getPesoID() {
        return PesoID;
    }

    public void setPesoID(String PesoID) {
        this.PesoID = PesoID;
    }

    public String getGrasaCoberturaID() {
        return GrasaCoberturaID;
    }

    public void setGrasaCoberturaID(String GrasaCoberturaID) {
        this.GrasaCoberturaID = GrasaCoberturaID;
    }

    public String getMusculoID() {
        return MusculoID;
    }

    public void setMusculoID(String MusculoID) {
        this.MusculoID = MusculoID;
    }

    public String getEstadoCriaID() {
        return EstadoCriaID;
    }

    public void setEstadoCriaID(String EstadoCriaID) {
        this.EstadoCriaID = EstadoCriaID;
    }

    public String getDietaID() {
        return DietaID;
    }

    public void setDietaID(String DietaID) {
        this.DietaID = DietaID;
    }

    public String getSensorID() {
        return SensorID;
    }

    public void setSensorID(String SensorID) {
        this.SensorID = SensorID;
    }

    public String getVecesEnTratamiento() {
        return VecesEnTratamiento;
    }

    public void setVecesEnTratamiento(String VecesEnTratamiento) {
        this.VecesEnTratamiento = VecesEnTratamiento;
    }
    
    // Métodos CRUD
    public static synchronized int añadirCria(Crias cria) {
        Statement conexion = SQLConnectionHelper.getConnection();
        if (conexion == null) {
            return Status.ERROR_CONNECTION;
        }
        try {
            conexion.execute("INSERT INTO Crias VALUES ("
                    + cria.CorralID + ", "
                    + cria.PesoID + ", "
                    + cria.GrasaCoberturaID + ", "
                    + cria.MusculoID + ", "
                    + cria.EstadoCriaID + ", "
                    + cria.DietaID + ", "
                    + cria.SensorID + ", "
                    + cria.VecesEnTratamiento + ");");
        } catch (SQLException e) {
            return Status.ERROR_INSERT;
        }
        return Status.OK_INSERT;
    }

}
