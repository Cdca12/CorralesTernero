package Entities;

/**
 *
 * @author Carlos Contreras
 */
public class TrasladosCrias {
    
    private String Transaccion;
    private String CorralID;
    private String CriasID;
    private String FechaIngreso;
    private String FechaEgreso;
    private String DiasEnCorral;
    
    public TrasladosCrias() {
        // Defaults
        this.FechaIngreso = "DEFAULT"; // CAST(GETDATE() as date)
        this.FechaEgreso = "NULL";
        this.DiasEnCorral = "DEFAULT"; // 0
    }
}
