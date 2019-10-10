package Entities;

/**
 *
 * @author Carlos Contreras
 */
public class Musculo {

    private String MusculoID;
    private String ColorMusculo;

    public Musculo() {
    }

    public String getMusculoID() {
        return MusculoID;
    }

    public void setMusculoID(String MusculoID) {
        this.MusculoID = MusculoID;
    }

    public String getColorMusculo() {
        return ColorMusculo;
    }

    public void setColorMusculo(String ColorMusculo) {
        this.ColorMusculo = ColorMusculo;
    }

    @Override
    public String toString() {
        return ColorMusculo;
    }

    // Métodos CRUD
    // TODO:
}
