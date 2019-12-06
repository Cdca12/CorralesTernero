package Utils;

/**
 *
 * @author Carlos Contreras
 */
public enum Status {
    
    OK_CONNECTION(0, "Conexión a la base de datos realizada correctamente.", "OK: Conexión SI realizada"),
    ERROR_CONNECTION(1, "No se pudo conectar a la base de datos. Compruebe su conexión a internet.", "ERROR: Conexión NO realizada"),
    
//    OK_INSERT_CRIA(2, "La cría se ha insertado correctamente.", "Éxito"),
//    ERROR_INSERT_CRIA(3, "Hubo un problema al insertar la cría. Verifique los datos nuevamente", "Error al insertar Cría"),
    
    ERROR_LOGIN(4, "El usuario o contraseña son incorrectos", "Error al ingresar"),
    
    OK_PRINT(5, "PDF Generado correctamente", "Éxito"),
    ERROR_PRINT(6, "No se pudo imprimir la tabla", "Error impresión"),
    
    OK_PROCESAR(7, "La cría seleccionada ha sido procesada correctamente", "Éxito"),
    ERROR_PROCESAR(8, "La cría seleccionada no ha podido ser procesada", "Error al procesar"),
    
    OK_PROCESAR_ALL(9, "Las crías han sido procesadas correctamente", "Éxito"),
    ERROR_PROCESAR_ALL(10, "Las crias no pudieron ser procesadas", "Error al procesar"),
    
    OK_SACRIFICAR(11, "La cría seleccionada ha sido sacrificada correctamente", "Éxito"),
    ERROR_SACRIFICAR(12, "La cría seleccionada no ha podido ser sacrificada", "Error al sacrificar"),
    
    OK_SACRIFICAR_ALL(13, "Las crías han sido sacrificadas correctamente", "Éxito"),
    ERROR_SACRIFICAR_ALL(14, "Las crias no pudieron ser sacrificadas", "Error al sacrificar"),
        
    OK_INSERT_CRIA(15, "La cría se ha insertado correctamente.", "Éxito"),
    ERROR_INSERT_CRIA(16, "Hubo un problema al insertar la cría. Verifique los datos nuevamente", "Error al insertar Cría"),
    
    OK_INSERT_ALIMENTO(17, "El alimento se ha insertado correctamente.", "Éxito"),
    ERROR_INSERT_ALIMENTO(18, "Hubo un problema al insertar el alimento. Verifique los datos nuevamente", "Error al insertar Alimento"),
    
    OK_INSERT_CORRAL(19, "El corral se ha insertado correctamente.", "Éxito"),
    ERROR_INSERT_CORRAL(20, "Hubo un problema al insertar el corral. Verifique los datos nuevamente", "Error al insertar Corral"),
    
    OK_INSERT_DIETA(20, "La dieta se ha insertado correctamente.", "Éxito"),
    ERROR_INSERT_DIETA(21, "Hubo un problema al insertar la dieta. Verifique los datos nuevamente", "Error al insertar Dieta"),
    
    OK_INSERT_SENSOR(20, "Los sensores se han insertado correctamente.", "Éxito"),
    ERROR_INSERT_SENSOR(21, "Hubo un problema al insertar los sensores. Verifique los datos nuevamente", "Error al insertar Sensor"),
    
    OK_ADD_CUARENTENA(22, "La cría seleccionada se ha añadido a cuarentena correctamente", "Éxito"),
    ERROR_ADD_CUARENTENA(23, "La cría seleccionada no ha podido añadir a cuarentena", "Error al añadir a cuarentena"),
    
    OK_ADD_CUARENTENA_ALL(24, "La crías se han añadido a cuarentena correctamente", "Éxito"),
    ERROR_ADD_CUARENTENA_ALL(25, "La crías no se han podido añadir a cuarentena", "Error al añadir a cuarentena"),
    
    OK_SIMULAR_SEÑALES(26, "Las señales de los sensores se han simulado correctamente", "Éxito"),
    ERROR_SIMULAR_SEÑALES(27, "Las señales de los sensores no se han podido simular", "Error al simular señales de sensores"),
    
    OK_AVANZAR_DIAS(28, "Se ha simulado el avance de días correcamente", "Éxito"),
    ERROR_AVANZAR_DIAS(29, "No se ha podido simular el avance de días", "Error al simular el avance de días");
    
    
    public final int CODE;
    public final String MESSAGE;
    public final String TITLE;
    
    private Status(int CODE, String MESSAGE, String TITLE) {
        this.CODE = CODE;
        this.MESSAGE = MESSAGE;
        this.TITLE = TITLE;
    }
    
}
