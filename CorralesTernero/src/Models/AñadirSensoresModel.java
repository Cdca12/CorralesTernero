package Models;

import Entities.Sensores;

/**
 *
 * @author Carlos Contreras
 */
public class AñadirSensoresModel {
    
    public AñadirSensoresModel() {
        
    }
    
    public synchronized int añadirSensor(Sensores sensor, int cantidad) {
        return Sensores.añadirSensor(sensor, cantidad);
    }
    
}
