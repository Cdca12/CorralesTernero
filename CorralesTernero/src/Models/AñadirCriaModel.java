package Models;

import Entities.Crias;

/**
 *
 * @author Carlos Contreras
 */
public class AñadirCriaModel {
    
    public AñadirCriaModel() {
        // Prueba añadir cria
        Crias cria = new Crias("", "1", "3", "2", "1", "1", "1", "NULL", "DEFAULT");
        añadirCria(cria);
    }
    
    public synchronized void añadirCria(Crias cria) {
        Crias.añadirCria(cria);
    }
    
    
    
    
    
}
