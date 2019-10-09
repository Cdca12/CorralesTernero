package Utils;

/**
 *
 * @author Carlos Contreras
 */
public final class Status {
    
    public final static int OK_CONNECTION = 0;
    public final static String OK_CONNECTION_TITLE = "OK: Conexión SI realizada";
    public final static String OK_CONNECTION_MESSAGE = "Conexión a la base de datos realizada correctamente.";
    
    public final static int ERROR_CONNECTION = 1;
    public final static String ERROR_CONNECTION_TITLE = "ERROR: Conexión NO realizada";
    public final static String ERROR_CONNECTION_MESSAGE = "No se pudo conectar a la base de datos. Compruebe su conexión a internet.";
    
    public final static int ERROR_INSERT = 2;
    public final static String ERROR_INSERT_TITLE = "ERROR: No se pudo insertar";
    public final static String ERROR_INSERT_MESSAGE = "Error de inserción. Verifique que los datos sean correctos.";
    
    public final static int OK_INSERT = 3;
    public final static String OK_INSERT_TITLE = "ÉXITO";
    public final static String OK_INSERT_MESSAGE = "Inserción realizada correctamente.";
}
