package Models;

import Entities.*;

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
    
//    public synchronized cria.getañadirCria(cria.getCorralID(),  cria.getPesoID(),  cria.getGrasaCoberturaID(),  cria.getMusculoID(),  cria.getEstadoCriaID(),  cria.getDietaID) {
//        return OldCrias.añadirCria(new OldCrias(""(),  CorralID(),  PesoID(),  GrasaCoberturaID(),  MusculoID(),  EstadoCriaID(),  DietaID(),  "NULL"(),  "0"));
//    }
//    
    public synchronized int añadirCria(String CorralID,  String PesoID,  String GrasaCoberturaID,  String MusculoID,  String EstadoCriaID,  String DietaID) {
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
