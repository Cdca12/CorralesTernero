package Utils;

/**
 *
 * @author Carlos Contreras
 */
public enum OtroStatus {
    
    OK_CONNECTION(0, "OK: Conexión SI realizada", "Conexión a la base de datos realizada correctamente."),
    ERROR_CONNECTION(1, "ERROR: Conexión NO realizada", "No se pudo conectar a la base de datos. Compruebe su conexión a internet."),
    OK_INSERT(2, "ÉXITO", "Inserción realizada correctamente."),
    ERROR_INSERT(3, "ERROR: No se pudo insertar", "Error de inserción. Verifique que los datos sean correctos.");
        
    public final int CODE;
    public final String TITLE;
    public final String MESSAGE;
    
    private OtroStatus(int CODE, String TITLE, String MESSAGE) {
        this.CODE = CODE;
        this.TITLE = TITLE;
        this.MESSAGE = MESSAGE;
    }
    
}
