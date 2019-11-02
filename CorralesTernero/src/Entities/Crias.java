package Entities;

import DataAccesor.SQLConnectionHelper;
import Utils.Status;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Clase que modela la entidad de Crias
 *
 * @author Carlos Contreras
 */
public class Crias {

    private String CriaID;
    private String CorralID;
    private String Peso;
    private String Grasa;
    private String GrasaCoberturaID;
    private String MusculoID;
    private String SensorID;
    private String DietaID;
    private String EstadoCriaID;
    private String DiasEdad;

    public Crias() {
        // Constructor vacío
    }

    public String getCriaID() {
        return CriaID;
    }

    public void setCriaID(String CriaID) {
        this.CriaID = CriaID;
    }

    public String getCorralID() {
        return CorralID;
    }

    public void setCorralID(String CorralID) {
        this.CorralID = CorralID;
    }

    public String getPeso() {
        return Peso;
    }

    public void setPeso(String Peso) {
        this.Peso = Peso;
    }

    public String getGrasa() {
        return Grasa;
    }

    public void setGrasa(String Grasa) {
        this.Grasa = Grasa;
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

    public String getSensorID() {
        return SensorID;
    }

    public void setSensorID(String SensorID) {
        this.SensorID = SensorID;
    }

    public String getDietaID() {
        return DietaID;
    }

    public void setDietaID(String DietaID) {
        this.DietaID = DietaID;
    }

    public String getEstadoCriaID() {
        return EstadoCriaID;
    }

    public void setEstadoCriaID(String EstadoCriaID) {
        this.EstadoCriaID = EstadoCriaID;
    }

    public String getDiasEdad() {
        return DiasEdad;
    }

    public void setDiasEdad(String DiasEdad) {
        this.DiasEdad = DiasEdad;
    }

    // Métodos CRUD
    public static synchronized int añadirCria(Crias cria) {
        Statement conexion = SQLConnectionHelper.getConnection();
        if (conexion == null) {
            return Status.ERROR_CONNECTION;
        }
        try {
            conexion.execute(
                    "INSERT INTO Crias (CorralID, Peso, Grasa, MusculoID, DietaID) "
                    + "VALUES ("
                    + cria.CorralID + ", "
                    + cria.Peso + ", "
                    + cria.Grasa + ", "
                    + cria.MusculoID + ", "
                    + cria.DietaID + ");");
        } catch (SQLException e) {
            return Status.ERROR_INSERT;
        }
        return Status.OK_INSERT;
    }

}
