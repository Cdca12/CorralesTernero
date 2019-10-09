package Models;

import EntitiesJPA.Crias;
import EntitiesJPA.EstadoCria;
import EntitiesJPA.Peso;
import EntitiesJPA.GrasaCobertura;
import EntitiesJPA.Musculo;
import EntitiesJPA.Corrales;
import EntitiesJPA.Dietas;
import Entities.*;
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
    
//    public synchronized int añadirCria(String CorralID, String PesoID, String GrasaCoberturaID, String MusculoID, String EstadoCriaID, String DietaID) {
//        return OldCrias.añadirCria(new OldCrias("", CorralID, PesoID, GrasaCoberturaID, MusculoID, EstadoCriaID, DietaID, "NULL", "0"));
//    }
//    
    public synchronized int añadirCria(Corrales CorralID, Peso PesoID, GrasaCobertura GrasaCoberturaID, Musculo MusculoID, EstadoCria EstadoCriaID, Dietas DietaID) {
        Crias cria = new Crias();
        cria.setCorralID(CorralID);
        cria.setPesoID(PesoID);
        cria.setGrasaCoberturaID(GrasaCoberturaID);
        cria.setMusculoID(MusculoID);
        cria.setEstadoCriaID(EstadoCriaID);
        cria.setDietaID(DietaID);
        return Crias.añadirCria(cria);
    }
    
    
}
