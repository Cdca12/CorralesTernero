package Entities;

/**
 *
 * @author Carlos Contreras
 */
public class TrasladosCrias {
    
    private String Transaccion;
    private String CorralID;
    private String CriaID;
    private String FechaIngreso;
    private String FechaEgreso;
    private String DiasEnCorral;
    
    public TrasladosCrias() {
        // Defaults
        this.FechaIngreso = "DEFAULT"; // CAST(GETDATE() as date)
        this.FechaEgreso = "NULL";
        this.DiasEnCorral = "DEFAULT"; // 0
    }

    public String getTransaccion() {
        return Transaccion;
    }

    public void setTransaccion(String Transaccion) {
        this.Transaccion = Transaccion;
    }

    public String getCorralID() {
        return CorralID;
    }

    public void setCorralID(String CorralID) {
        this.CorralID = CorralID;
    }

    public String getCriaID() {
        return CriaID;
    }

    public void setCriaID(String CriaID) {
        this.CriaID = CriaID;
    }

    public String getFechaIngreso() {
        return FechaIngreso;
    }

    public void setFechaIngreso(String FechaIngreso) {
        this.FechaIngreso = FechaIngreso;
    }

    public String getFechaEgreso() {
        return FechaEgreso;
    }

    public void setFechaEgreso(String FechaEgreso) {
        this.FechaEgreso = FechaEgreso;
    }

    public String getDiasEnCorral() {
        return DiasEnCorral;
    }

    public void setDiasEnCorral(String DiasEnCorral) {
        this.DiasEnCorral = DiasEnCorral;
    }
    
    // Métodos CRUD
    // TODO:
}
