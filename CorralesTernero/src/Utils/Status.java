package Utils;

/**
 *
 * @author Carlos Contreras
 */
public final class Status {
    
    public final static int OKAY = 0;
    
    public final static int ERROR_CONEXION = 0;
    public final static String ERROR_CONEXION_TITLE = "ERROR: Conexión NO realizada";
    public final static String ERROR_CONEXION_MESSAGE = "No se pudo conectar a la base de datos. Compruebe su conexión a internet.";
    
    public final static int ERROR_INSERCION = 1;
    public final static String ERROR_INSERCION_TITLE = "ERROR: No se pudo insertar";
    public final static String ERROR_INSERCION_MESSAGE = "Error de inserción. Verifique que los datos sean correctos.";
    
}
