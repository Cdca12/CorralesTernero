package Models;

import Entities.Sensores;
import Utils.Status;

/**
 *
 * @author Carlos Contreras
 */
public class AñadirSensoresModel {
    
    public AñadirSensoresModel() {
        
    }
    
    public synchronized Status añadirSensor(Sensores sensor, int cantidad) {
        return Sensores.añadirSensor(sensor, cantidad);
    }
    
}
