package Entities;

/**
 *
 * @author Carlos Contreras
 */
public class Dietas {

    private String DietaID;
    private String DiasTratamiento;
    private String AlimentoID;

    public Dietas() {
    }

    public String getDietaID() {
        return DietaID;
    }

    public void setDietaID(String DietaID) {
        this.DietaID = DietaID;
    }

    public String getDiasTratamiento() {
        return DiasTratamiento;
    }

    public void setDiasTratamiento(String DiasTratamiento) {
        this.DiasTratamiento = DiasTratamiento;
    }

    public String getAlimentoID() {
        return AlimentoID;
    }

    public void setAlimentoID(String AlimentoID) {
        this.AlimentoID = AlimentoID;
    }

    @Override
    public String toString() {
        return DietaID;
    }

    // Métodos CRUD
    // TODO:
}
