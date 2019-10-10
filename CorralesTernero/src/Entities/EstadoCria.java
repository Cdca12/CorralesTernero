package Entities;

/**
 *
 * @author Carlos Contreras
 */
public class EstadoCria {

    private String EstadoCriaID;
    private String Descripcion;

    public EstadoCria() {
    }

    public String getEstadoCriaID() {
        return EstadoCriaID;
    }

    public void setEstadoCriaID(String EstadoCriaID) {
        this.EstadoCriaID = EstadoCriaID;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    @Override
    public String toString() {
        return Descripcion;
    }

    // Métodos CRUD
    // TODO:
}
