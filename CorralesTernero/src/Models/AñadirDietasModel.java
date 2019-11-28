package Models;

import Entities.*;
/**
 *
 * @author Carlos Contreras
 */
public class AñadirDietasModel {

    public AñadirDietasModel() {
        
    }
    
    public synchronized int añadirDieta(Dietas dieta) {
        return Dietas.añadirDieta(dieta);
    }
}
