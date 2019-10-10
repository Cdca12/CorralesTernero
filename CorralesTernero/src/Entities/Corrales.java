package Entities;

/**
 *
 * @author Carlos Contreras
 */
public class Corrales {
    
    private String CorralID;
    private String EstadoID;
    private String TipoCorralID;
    
    public Corrales() {
    }

    public String getCorralID() {
        return CorralID;
    }

    public void setCorralID(String CorralID) {
        this.CorralID = CorralID;
    }

    public String getEstadoID() {
        return EstadoID;
    }

    public void setEstadoID(String EstadoID) {
        this.EstadoID = EstadoID;
    }

    public String getTipoCorralID() {
        return TipoCorralID;
    }

    public void setTipoCorralID(String TipoCorralID) {
        this.TipoCorralID = TipoCorralID;
    }
    
    @Override
    public String toString() {
        return CorralID;
    }
    
    
    // Métodos CRUD
    // TODO:

    
}
