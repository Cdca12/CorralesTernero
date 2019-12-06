package Models;

import DataAccesor.SQLConnectionHelper;
import Utils.Status;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Carlos Contreras
 */
public class SimularSeñalesModel {

    public SimularSeñalesModel() {

    }

    public synchronized Status simularSeñales(String sensorID, int numeroSimulaciones) {
        int presionArterial, respiracion, pulso, temperatura;
        Statement conexion = SQLConnectionHelper.getConnection();
        if (conexion == null) {
            return Status.ERROR_CONNECTION;
        }
        presionArterial = respiracion = pulso = temperatura = 0;
        for (int i = 0; i < numeroSimulaciones; i++) {
            presionArterial = (int) (Math.random() * ((120 - 80) + 1)) + 80; // 80-120
            respiracion = (int) (Math.random() * ((6 - 4) + 1)) + 4;         // 4-6 lts aire por minuto
            pulso = (int) (Math.random() * ((90 - 60) + 1)) + 60;            // 60-90 latidos por minuto
            temperatura = (int) (Math.random() * ((45 - 35) + 1)) + 35;      // 35-45 grados centigrados
            try {
                conexion.execute(
                        "INSERT INTO SeñalesSensores "
                        + "VALUES ("
                        + sensorID + ", "
                        + presionArterial + ", "
                        + respiracion + ", "
                        + pulso + ", "
                        + temperatura + ");");
            } catch (SQLException e) {
                return Status.ERROR_SIMULAR_SEÑALES;
            }
        }
        return Status.OK_SIMULAR_SEÑALES;
    }
}
