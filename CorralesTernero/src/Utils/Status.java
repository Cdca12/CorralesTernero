package Utils;

/**
 *
 * @author Carlos Contreras
 */
public enum Status {
    
    OK_CONNECTION(0, "Conexión a la base de datos realizada correctamente.", "OK: Conexión SI realizada"),
    ERROR_CONNECTION(1, "No se pudo conectar a la base de datos. Compruebe su conexión a internet.", "ERROR: Conexión NO realizada"),
    
    OK_INSERT(2, "Inserción realizada correctamente.", "Éxito"),
    ERROR_INSERT(3, "Verifique que los datos sean correctos.", "Error al insertar"),
    
    ERROR_LOGIN(4, "El usuario o contraseña son incorrectos", "Error al ingresar"),
    
    OK_PRINT(5, "PDF Generado correctamente", "Éxito"),
    ERROR_PRINT(6, "No se pudo imprimir la tabla", "Error impresión"),
    
    OK_PROCCESS(7, "La cría seleccionada ha sido procesada correctamente", "Éxito"),
    ERROR_PROCCESS(8, "La cría seleccionada no ha podido ser procesada", "Error al procesar"),
    
    OK_PROCCESS_ALL(9, "Las crías han sido procesadas correctamente", "Éxito"),
    ERROR_PROCCESS_ALL(10, "Las crias no pudieron ser procesadas", "Error al procesar");
        
    public final int CODE;
    public final String MESSAGE;
    public final String TITLE;
    
    private Status(int CODE, String MESSAGE, String TITLE) {
        this.CODE = CODE;
        this.MESSAGE = MESSAGE;
        this.TITLE = TITLE;
    }
    
}
