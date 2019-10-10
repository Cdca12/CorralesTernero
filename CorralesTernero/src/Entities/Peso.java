package Entities;

/**
 *
 * @author Carlos Contreras
 */
public class Peso {

    private String PesoID;
    private String CondicionCorporal;

    public Peso() {
    }

    public String getPesoID() {
        return PesoID;
    }

    public void setPesoID(String PesoID) {
        this.PesoID = PesoID;
    }

    public String getCondicionCorporal() {
        return CondicionCorporal;
    }

    public void setCondicionCorporal(String CondicionCorporal) {
        this.CondicionCorporal = CondicionCorporal;
    }
    
    @Override
    public String toString() {
        return CondicionCorporal;
    }

    // Métodos CRUD
    // TODO:
}
