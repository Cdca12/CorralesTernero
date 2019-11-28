package Entities;

import DataAccesor.SQLConnectionHelper;
import Utils.Status;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Carlos Contreras
 */
public class Dietas {

    private String DietaID;
    private String DiasTratamiento;
    private String AlimentoID;

    public Dietas() {
    }

    public String getDietaID() {
        return DietaID;
    }

    public void setDietaID(String DietaID) {
        this.DietaID = DietaID;
    }

    public String getDiasTratamiento() {
        return DiasTratamiento;
    }

    public void setDiasTratamiento(String DiasTratamiento) {
        this.DiasTratamiento = DiasTratamiento;
    }

    public String getAlimentoID() {
        return AlimentoID;
    }

    public void setAlimentoID(String AlimentoID) {
        this.AlimentoID = AlimentoID;
    }

    @Override
    public String toString() {
        return DietaID;
    }

    // Métodos CRUD
    public static synchronized int añadirDieta(Dietas dieta) {
        Statement conexion = SQLConnectionHelper.getConnection();
        if (conexion == null) {
            return Status.ERROR_CONNECTION;
        }
        try {
            conexion.execute(
                    "INSERT INTO Dietas (DiasTratamiento, AlimentoID) "
                    + "VALUES ("
                    + dieta.DiasTratamiento + ", "
                    + dieta.AlimentoID + ");");
        } catch (SQLException e) {
            return Status.ERROR_INSERT;
        }
        return Status.OK_INSERT;
    }
}
