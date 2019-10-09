package Models;

import Entities.Crias;

/**
 *
 * @author Carlos Contreras
 */
public class AñadirCriaModel {
    
    public AñadirCriaModel() {
    }
    
    public synchronized int añadirCria(Crias cria) {
        return Crias.añadirCria(cria);
    }
    
    public synchronized int añadirCria(String CorralID, String PesoID, String GrasaCoberturaID, String MusculoID, String EstadoCriaID, String DietaID) {
        return Crias.añadirCria(new Crias("", CorralID, PesoID, GrasaCoberturaID, MusculoID, EstadoCriaID, DietaID, "NULL", "0"));
    }
    
    
    
}
