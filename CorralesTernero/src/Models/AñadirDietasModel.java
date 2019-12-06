package Models;

import Entities.*;
import Utils.Status;
/**
 *
 * @author Carlos Contreras
 */
public class AñadirDietasModel {

    public AñadirDietasModel() {
        
    }
    
    public synchronized Status añadirDieta(Dietas dieta) {
        return Dietas.añadirDieta(dieta);
    }
}
